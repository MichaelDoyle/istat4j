/* 
 * Copyright 2013 Michael J Doyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.md.istat4j.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.md.istat4j.model.Disk;
import com.md.istat4j.model.Load;
import com.md.istat4j.model.Memory;
import com.md.istat4j.model.Telemetry;
import com.md.istat4j.model.TelemetryCpu;
import com.md.istat4j.model.TelemetryNetwork;
import com.md.istat4j.model.xml.v1.IsrAuthenticationResponse;
import com.md.istat4j.model.xml.v1.IsrConnectionTest;
import com.md.istat4j.model.xml.v1.IsrConnectionTestRequest;
import com.md.istat4j.model.xml.v1.IsrConnectionTestResponse;
import com.md.istat4j.model.xml.v1.IsrDisk;
import com.md.istat4j.model.xml.v1.IsrLoad;
import com.md.istat4j.model.xml.v1.IsrMemory;
import com.md.istat4j.model.xml.v1.IsrNetworkInterface;
import com.md.istat4j.model.xml.v1.IsrRegistrationRequest;
import com.md.istat4j.model.xml.v1.IsrRegistrationResponse;
import com.md.istat4j.model.xml.v1.IsrTelemetryCpu;
import com.md.istat4j.model.xml.v1.IsrTelemetryNetwork;
import com.md.istat4j.model.xml.v1.IsrTelemetryRequest;
import com.md.istat4j.model.xml.v1.IsrTelemetryResponse;
import com.md.istat4j.model.xml.v1.IsrUptime;

public class IstatServiceImpl implements IstatService {

	private static final Logger logger = LoggerFactory.getLogger(IstatServiceImpl.class);

	private static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	private Serializer serializer;
	
	private ServerConfiguration sc;

	private int requestId = 1;
	
	public IstatServiceImpl() {
		Format format = new Format(0, PROLOG); 
		serializer = new Persister(new AnnotationStrategy(), format);
	}
	
	public IstatServiceImpl(ServerConfiguration sc) {
		Format format = new Format(0, PROLOG); 
		serializer = new Persister(new AnnotationStrategy(), format);
		
		sc.validate();
		this.sc = sc;
	}
	
	@Override
	public void setServerConfiguration(ServerConfiguration sc) {		
		sc.validate();
		this.sc = sc;
	}
	
	@Override
	public Telemetry getTelemetry() throws IstatException {
		return getTelemetry(-1);
	}

	@Override
	public Telemetry getAllTelemetry() throws IstatException {
		return getTelemetry(-2);
	}
	
	@Override
	public Telemetry getTelemetry(long since) throws IstatException {
		sc.validate();
		
		Socket socket = null;
		DataOutputStream os = null;
		DataInputStream is = null;
		IsrRegistrationResponse reg = null;
		IsrTelemetryResponse tel = null;
		
		try {
			socket = new Socket();
			socket.setSoTimeout(5000);			
			socket.connect(
					new InetSocketAddress(sc.getServerHostname(), sc.getServerPort()), 3000);
			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());
			
			if (testConnection(os, is)) {
				reg = register(os, is);
				if(authenticate(os, is)) {
					tel = getTelemetry(os, is, since);
				}
			}
		} catch (IOException e) {
			logger.info("Exception connecting to iStat.", e);
			throw new IstatException("Connection to iStat failed.", e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				logger.info("Exception cleaning up.", e);
			}
		}
		
		return transformTelemetryResponse(reg, tel);
	}
	
	private boolean testConnection(DataOutputStream os, DataInputStream is) throws IstatException {
		IsrConnectionTestRequest ctr = new IsrConnectionTestRequest();
		ctr.setConnTest(new IsrConnectionTest());
		
		StringWriter writer = new StringWriter();
		try {
			serializer.write(ctr, writer);
		} catch (Exception e) {
			logger.info("Exception testing connection with iStat.", e);
			throw new IstatException("Connection test to iStat failed.", e);
		}
		
		IsrConnectionTestResponse response = null;
		
		try {
			os.write(writer.toString().replace("\n", "").getBytes());
		    response = getResponse(IsrConnectionTestResponse.class, is);
		} catch (IOException e) {
			logger.info("Exception testing connection with iStat.", e);
			throw new IstatException("Connection test to iStat failed.", e);
		}
		
		return response != null;
	}

	private IsrRegistrationResponse register(DataOutputStream os, DataInputStream is) throws IstatException {
		IsrRegistrationRequest rr = new IsrRegistrationRequest();
		rr.setHostname(sc.getClientHostname());
		rr.setUuid(sc.getClientUuid());

		StringWriter writer = new StringWriter();
		
		try {
			serializer.write(rr, writer);
		} catch (Exception e) {
			logger.info("Exception registering with iStat.", e);
			throw new IstatException("Registration with iStat failed.", e);
		}
		
		IsrRegistrationResponse response = null;
		
		try {
			os.write(writer.toString().replace("\n", "").getBytes());
		    response = getResponse(IsrRegistrationResponse.class, is);
		} catch (IOException e) {
			logger.info("Exception registering with iStat.", e);
			throw new IstatException("Registration with iStat failed.", e);
		}
		
		return response;
	}

	private boolean authenticate(DataOutputStream os, DataInputStream is) throws IstatException {
		
		try {
			os.write(sc.getServerPassword().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.info("Exception authenticating with iStat.", e);
			throw new IstatException("Authentication with iStat failed.", e);
		} catch (IOException e) {
			logger.info("Exception authenticating with iStat.", e);
			throw new IstatException("Authentication with iStat failed.", e);
		}
		
		IsrAuthenticationResponse response = getResponse(IsrAuthenticationResponse.class, is);
		
		return response != null && response.getReady() == 1;
	}
	
	private IsrTelemetryResponse getTelemetry(DataOutputStream os, DataInputStream is, long since) 
			throws IstatException {
		IsrTelemetryRequest tr = new IsrTelemetryRequest();
		tr.setRequestId(requestId++);
		tr.setCpu(since);
		tr.setNetwork(since);
		tr.setDisk(since);
		tr.setFan(since);
		tr.setLoad(since);
		tr.setMemory(since);
		tr.setTemp(since);
		tr.setUptime(since);
		
		StringWriter writer = new StringWriter();
		
		try {
			serializer.write(tr, writer);
		} catch (Exception e) {
			logger.info("Exception retrieving telemetry from iStat.", e);
			throw new IstatException("Request to iStat for telemetry failed.", e);
		}
		
		IsrTelemetryResponse response = null;
		
		try {
			os.write(writer.toString().replace("\n", "").getBytes());
		    response = getResponse(IsrTelemetryResponse.class, is);
		} catch (IOException e) {
			logger.info("Exception retrieving telemetry from iStat.", e);
			throw new IstatException("Request to iStat for telemetry failed.", e);
		}
		
		return response;
	}
	
	private <T> T getResponse(Class<T> clazz, DataInputStream is) throws IstatException {
		String answer = "";
		
		try {
			while (true) {
				byte b = is.readByte();
				answer += (char) b;
				if (answer.endsWith("</isr>")) {
					break;
				}
			}
		} catch (IOException e) {
			logger.info("Exception reading response from iStat", e);
			throw new IstatException("Failure receiving response from iStat.", e);
		}
		
		logger.debug("Answer from iStat: {}", answer);
		
		T retval = null;
		
		try {
			retval = serializer.read(clazz, answer);
		} catch (Exception e) {
			logger.info("Exception deserializing XML", e);
			throw new IstatException("Failure parsing response from iStat.", e);
		}
		
		return retval;
	}
	
	private Telemetry transformTelemetryResponse(IsrRegistrationResponse reg, IsrTelemetryResponse tel) {
		Telemetry telemetry = new Telemetry();
		telemetry.setRequestId(tel.getRequestId());
		telemetry.setTempSid(tel.getTempSid());
		telemetry.setDiskSid(tel.getDiskSid());
		telemetry.setFanSid(tel.getFanSid());
		
		IsrUptime isrUptime = tel.getUptime();
		if (isrUptime != null) {
			telemetry.setUptime(isrUptime.getUptime());
		}
		
		IsrMemory isrMem = tel.getMemory();
		if (isrMem != null) {
			Memory memory = new Memory();
			memory.setActive(isrMem.getActive());
			memory.setFree(isrMem.getFree());
			memory.setInactive(isrMem.getInactive());
			memory.setPageIns(isrMem.getPageIns());
			memory.setPageOuts(isrMem.getPageOuts());
			memory.setSwapTotal(isrMem.getSwapTotal());
			memory.setSwapUsed(isrMem.getSwapUsed());
			memory.setTotal(isrMem.getTotal());
			memory.setWired(isrMem.getWired());
			telemetry.setMemory(memory);
		}
		
		IsrLoad isrLoad = tel.getLoad();
		if (isrLoad != null) {
			Load load = new Load();
			load.setOneMinuteAverage(isrLoad.getOneMinuteAverage());
			load.setFiveMinuteAverage(isrLoad.getFiveMinuteAverage());
			load.setFifteenMinuteAverage(isrLoad.getFifteenMinuteAverage());
			telemetry.setLoad(load);
		}
		
		List<Disk> disks = new ArrayList<Disk>();
		List<IsrDisk> isrDisks = tel.getDisks();
		for (IsrDisk d : isrDisks) {
			Disk disk = new Disk();
			disk.setFreeMb(d.getFree());
			disk.setName(d.getName());
			disk.setPercentUsed(d.getPercentUsed());
			disk.setUuid(d.getUuid());
			disks.add(disk);
		}
		telemetry.setDisks(disks);
		
		List<TelemetryCpu> telemetryCpu = new ArrayList<TelemetryCpu>();
		List<IsrTelemetryCpu> isrTelCpu = tel.getCpuTelemetry();
		for (IsrTelemetryCpu c : isrTelCpu) {
			TelemetryCpu cpu = new TelemetryCpu();
			cpu.setId(c.getId());
			cpu.setNice(c.getNice());
			cpu.setSystem(c.getSystem());
			cpu.setUser(c.getUser());
			telemetryCpu.add(cpu);
		}
		telemetry.setCpuTelemetry(telemetryCpu);
		
		List<TelemetryNetwork> telemetryNet = new ArrayList<TelemetryNetwork>();
		IsrNetworkInterface isrIface = tel.getNetworkInterface();
		List<IsrTelemetryNetwork> isrTelNet = isrIface.getTelemetry();
		for (IsrTelemetryNetwork n : isrTelNet) {
			TelemetryNetwork net = new TelemetryNetwork();
			net.setId(n.getId());
			net.setDownloadKb(n.getDown());
			net.setUploadKb(n.getUp());
			net.setTime(new Date(n.getTime()));
			telemetryNet.add(net);
		}
		telemetry.setNetworkTelemetry(telemetryNet);
		
		return telemetry;
	}
}
