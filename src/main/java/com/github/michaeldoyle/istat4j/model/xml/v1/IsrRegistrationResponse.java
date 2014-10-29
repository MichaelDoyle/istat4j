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

@Root(name="isr", strict=false)
public class IsrRegistrationResponse {
		      
	@Attribute(name="pl", required=true)
	private int platform;
	
	@Attribute(name="ath", required=true)
	private int authorizationRequired;
	
	@Attribute(name="ss", required=true)
	private int ss;
	
	@Attribute(name="c", required=true)
	private BigInteger nextCpuId;
	
	@Attribute(name="n", required=true)
	private BigInteger nextNetworInterfaceId;
				
	public IsrRegistrationResponse() {
		// necessary to keep xml deserialization happy
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public int getAuthorizationRequired() {
		return authorizationRequired;
	}

	public void setAuthorizationRequired(int authorizationRequired) {
		this.authorizationRequired = authorizationRequired;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
	}

	public BigInteger getNextCpuId() {
		return nextCpuId;
	}

	public void setNextCpuId(BigInteger nextCpuId) {
		this.nextCpuId = nextCpuId;
	}

	public BigInteger getNextNetworInterfaceId() {
		return nextNetworInterfaceId;
	}

	public void setNextNetworInterfaceId(BigInteger nextNetworInterfaceId) {
		this.nextNetworInterfaceId = nextNetworInterfaceId;
	}
}
