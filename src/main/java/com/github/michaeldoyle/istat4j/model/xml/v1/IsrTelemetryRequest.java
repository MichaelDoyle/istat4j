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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="isr", strict=false)
public class IsrTelemetryRequest {
	
	@Element(name="rid", required=true)
	private long requestId;
	
	@Element(name="c", required=true)
	private long cpu;
	
	@Element(name="n", required=true)
	private long network;
	
	@Element(name="m", required=true)
	private long memory;
	
	@Element(name="lo", required=true)
	private long load;
	
	@Element(name="t", required=true)
	private long temp;
	
	@Element(name="f", required=true)
	private long fan;
	
	@Element(name="u", required=true)
	private long uptime;
	
	@Element(name="d", required=true)
	private long disk;

	public IsrTelemetryRequest() {
		// necessary to keep xml deserialization happy
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public long getCpu() {
		return cpu;
	}

	public void setCpu(long cpu) {
		this.cpu = cpu;
	}

	public long getNetwork() {
		return network;
	}

	public void setNetwork(long network) {
		this.network = network;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public long getLoad() {
		return load;
	}

	public void setLoad(long load) {
		this.load = load;
	}

	public long getTemp() {
		return temp;
	}

	public void setTemp(long temp) {
		this.temp = temp;
	}

	public long getFan() {
		return fan;
	}

	public void setFan(long fan) {
		this.fan = fan;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getDisk() {
		return disk;
	}

	public void setDisk(long disk) {
		this.disk = disk;
	}
}
