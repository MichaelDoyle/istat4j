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
package com.md.istat4j.model.xml.v1;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="isr", strict=false)
public class IsrRegistrationRequest {

	@Element(name="h", required=true)
	private String hostname;
	
	@Element(name="duuid", required=true)
	private String uuid;
	
	public IsrRegistrationRequest() {
		// necessary to keep xml deserialization happy
	}

	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname hostname of the client
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid unique identifier for the client
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
