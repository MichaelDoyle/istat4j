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
package com.github.michaeldoyle.istat4j.model.xml.v1;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="isr", strict=false)
public class IsrTelemetryResponse {

	@Attribute(name="rid", required=true)
	private long requestId;
	
	@Attribute(name="ds", required=true)
	private long diskSid;
	
	@Attribute(name="ts", required=true)
	private long tempSid;
	
	@Attribute(name="fs", required=true)
	private long fanSid;
	
	@Path("CPU")
	@ElementList(entry="c", inline=true, empty=false)
	private List<IsrTelemetryCpu> cpuTelemetry;
	
	@Element(name="NET", required=true)
	private IsrNetworkInterface networkInterface;
	
	@Path("DISKS")
	@ElementList(entry="d", inline=true, empty=false)
	private List<IsrDisk> disks;
	
	@Element(name="LOAD", required=true)
	private IsrLoad load;
	
	@Element(name="MEM", required=true)
	private IsrMemory memory;
	
	@Element(name="UPT", required=true)
	private IsrUptime uptime;
	
	public IsrTelemetryResponse() {
		// necessary to keep xml deserialization happy
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public long getDiskSid() {
		return diskSid;
	}

	public void setDiskSid(long diskSid) {
		this.diskSid = diskSid;
	}

	public long getTempSid() {
		return tempSid;
	}

	public void setTempSid(long tempSid) {
		this.tempSid = tempSid;
	}

	public long getFanSid() {
		return fanSid;
	}

	public void setFanSid(long fanSid) {
		this.fanSid = fanSid;
	}

	public List<IsrTelemetryCpu> getCpuTelemetry() {
		return cpuTelemetry;
	}

	public void setCpuTelemetry(List<IsrTelemetryCpu> cpuTelemetry) {
		this.cpuTelemetry = cpuTelemetry;
	}

	public IsrNetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	public void setNetworkInterface(IsrNetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}

	public List<IsrDisk> getDisks() {
		return disks;
	}

	public void setDisks(List<IsrDisk> disks) {
		this.disks = disks;
	}

	public IsrLoad getLoad() {
		return load;
	}

	public void setLoad(IsrLoad load) {
		this.load = load;
	}

	public IsrMemory getMemory() {
		return memory;
	}

	public void setMemory(IsrMemory memory) {
		this.memory = memory;
	}

	public IsrUptime getUptime() {
		return uptime;
	}

	public void setUptime(IsrUptime uptime) {
		this.uptime = uptime;
	}
}
