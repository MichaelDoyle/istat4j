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
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class IsrNetworkInterface {

	@Attribute(name="if", required=true)
	private String interfaceName;
	
	@ElementList(name="n", inline=true, empty=false)
	private List<IsrTelemetryNetwork> telemetry;
	
	public IsrNetworkInterface() {
		// necessary to keep xml deserialization happy
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public List<IsrTelemetryNetwork> getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(List<IsrTelemetryNetwork> telemetry) {
		this.telemetry = telemetry;
	}
}
