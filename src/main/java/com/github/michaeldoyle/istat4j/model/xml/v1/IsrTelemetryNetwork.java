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

@Root(strict=false, name="n")
public class IsrTelemetryNetwork {
	
	@Attribute(name="id", required=true)
	private long id;
    
	@Attribute(name="d", required=true)
	private BigInteger down;
	
	@Attribute(name="u", required=true)
	private BigInteger up;
	
	@Attribute(name="t", required=true)
	private long time;
            
	public IsrTelemetryNetwork() {
		// necessary to keep xml deserialization happy
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigInteger getDown() {
		return down;
	}

	public void setDown(BigInteger down) {
		this.down = down;
	}

	public BigInteger getUp() {
		return up;
	}

	public void setUp(BigInteger up) {
		this.up = up;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
