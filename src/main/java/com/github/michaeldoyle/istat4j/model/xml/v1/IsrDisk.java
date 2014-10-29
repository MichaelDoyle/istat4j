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

import java.math.BigInteger;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="d", strict=false)
public class IsrDisk {

	@Attribute(name="uuid", required=true)
	private String uuid;
    
	@Attribute(name="n", required=true)
	private String name;
	
	@Attribute(name="f", required=true)
	private BigInteger free;
	
	@Attribute(name="p", required=true)
	private double percentUsed;
	
	public IsrDisk() {
		// necessary to keep xml deserialization happy
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getFree() {
		return free;
	}

	public void setFree(BigInteger free) {
		this.free = free;
	}

	public double getPercentUsed() {
		return percentUsed;
	}

	public void setPercentUsed(double percentUsed) {
		this.percentUsed = percentUsed;
	}
}
