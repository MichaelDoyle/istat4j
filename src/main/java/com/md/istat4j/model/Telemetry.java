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
package com.md.istat4j.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Telemetry {

	private long requestId;
	private long uptime;
	private long diskSid;
	private long tempSid;
	private long fanSid;

	private Load load;
	private Memory memory;
	
	private List<TelemetryCpu> cpuTelemetry;
	private List<TelemetryNetwork> networkTelemetry;
	private List<Disk> disks;
	
	public long getRequestId() {
		return requestId;
	}
	
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	
	public long getUptime() {
		return uptime;
	}
	
	public void setUptime(long uptime) {
		this.uptime = uptime;
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
	
	public Load getLoad() {
		return load;
	}
	
	public void setLoad(Load load) {
		this.load = load;
	}
	
	public Memory getMemory() {
		return memory;
	}
	
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	
	public List<TelemetryCpu> getCpuTelemetry() {
		return cpuTelemetry;
	}
	
	public void setCpuTelemetry(List<TelemetryCpu> cpuTelemetry) {
		this.cpuTelemetry = cpuTelemetry;
	}
	
	public List<TelemetryNetwork> getNetworkTelemetry() {
		return networkTelemetry;
	}
	
	public void setNetworkTelemetry(List<TelemetryNetwork> networkTelemetry) {
		this.networkTelemetry = networkTelemetry;
	}
	
	public List<Disk> getDisks() {
		return disks;
	}
	
	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}
	
	public String getDisplayUptime() {
		long day = TimeUnit.MINUTES.toDays(uptime);        
		long hours = TimeUnit.MINUTES.toHours(uptime) - (day * 24);
		long minute = TimeUnit.MINUTES.toMinutes(uptime) - (TimeUnit.MINUTES.toHours(uptime) * 60);
		return day + " days, " + hours + " hours, " + minute + " minutes";
	}
}
