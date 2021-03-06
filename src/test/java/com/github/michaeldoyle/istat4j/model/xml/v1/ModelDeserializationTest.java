/* 
 * Copyright 2013-2014 Michael J Doyle
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
package com.github.michaeldoyle.istat4j.model.xml.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ModelDeserializationTest {

	private static Serializer serializer;

	@BeforeClass
	public static void setUp() {
		serializer = new Persister();
	}

	@Test
	public void testConnectionTestResponse() throws Exception {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("xml/v1/connectionTestResponse.xml");

		IsrConnectionTestResponse response = serializer.read(
				IsrConnectionTestResponse.class, in);

		assertNotNull(response);
	}

	@Test
	public void testRegistrationResponse() throws Exception {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("xml/v1/registrationResponse.xml");

		IsrRegistrationResponse response = serializer.read(
				IsrRegistrationResponse.class, in);

		assertEquals(1, response.getAuthorizationRequired());
		assertEquals(2, response.getPlatform());
		assertEquals(6, response.getSs());
		assertEquals(BigInteger.valueOf(1109946), response.getNextCpuId());
		assertEquals(BigInteger.valueOf(1109945), response.getNextNetworInterfaceId());
	}

	@Test
	public void testAuthorizationResponse() throws Exception {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("xml/v1/authorizationResponse.xml");

		IsrAuthenticationResponse response = serializer.read(
				IsrAuthenticationResponse.class, in);

		assertEquals(1, response.getReady());
	}

	@Test
	public void testTelemetryResponse() throws Exception {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("xml/v1/telemetryResponse.xml");

		IsrTelemetryResponse response = serializer.read(IsrTelemetryResponse.class,
				in);

		assertEquals(1, response.getRequestId());
		assertEquals(BigInteger.valueOf(0), response.getDiskSid());
		assertEquals(BigInteger.valueOf(0), response.getFanSid());
		assertEquals(BigInteger.valueOf(0), response.getTempSid());

		List<IsrTelemetryCpu> cpu = response.getCpuTelemetry();
		assertEquals(299, cpu.size());

		IsrTelemetryCpu tc = cpu.get(0);
		assertEquals(BigInteger.valueOf(1330), tc.getId());
		assertEquals(0, tc.getNice());
		assertEquals(1, tc.getSystem());
		assertEquals(0, tc.getUser());

		IsrNetworkInterface networkInterface = response.getNetworkInterface();
		assertEquals("1", networkInterface.getInterfaceName());

		List<IsrTelemetryNetwork> tni = networkInterface.getTelemetry();
		assertEquals(300, tni.size());

		IsrTelemetryNetwork tn = tni.get(0);
		assertEquals(BigInteger.valueOf(1329), tn.getId());
		assertEquals(BigInteger.valueOf(33545899), tn.getDown());
		assertEquals(BigInteger.valueOf(4413428), tn.getUp());
		assertEquals(1386127188, tn.getTime());

		List<IsrDisk> disks = response.getDisks();
		assertEquals(3, disks.size());

		IsrLoad load = response.getLoad();
		assertEquals(0.82, load.getOneMinuteAverage(), 0.0);
		assertEquals(0.76, load.getFiveMinuteAverage(), 0.0);
		assertEquals(0.69, load.getFifteenMinuteAverage(), 0.0);

		IsrMemory memory = response.getMemory();
		assertEquals(BigInteger.valueOf(315), memory.getActive());
		assertEquals(BigInteger.valueOf(548), memory.getFree());
		assertEquals(BigInteger.valueOf(117), memory.getInactive());
		assertEquals(BigInteger.valueOf(0), memory.getPageIns());
		assertEquals(BigInteger.valueOf(0), memory.getPageOuts());
		assertEquals(BigInteger.valueOf(523), memory.getSwapTotal());
		assertEquals(BigInteger.valueOf(0), memory.getSwapUsed());
		assertEquals(BigInteger.valueOf(1264), memory.getTotal());
		assertEquals(BigInteger.valueOf(283), memory.getWired());

		IsrUptime uptime = response.getUptime();
		assertEquals(1628, uptime.getUptime());
	}
}
