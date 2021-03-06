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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="isr", strict=false)
public class IsrAuthenticationResponse {

	@Attribute(name="ready", required=false)
	private int ready;
	
	@Attribute(name="athrej", required=false)	
	private int athrej;
	
	public IsrAuthenticationResponse() {
		// necessary to keep xml deserialization happy
	}

	public int getReady() {
		return ready;
	}

	public void setReady(int ready) {
		this.ready = ready;
	}

	public int getAthrej() {
		return athrej;
	}

	public void setAthrej(int athrej) {
		this.athrej = athrej;
	}
}
