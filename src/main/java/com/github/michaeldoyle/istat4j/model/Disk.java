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
package com.github.michaeldoyle.istat4j.model;

import java.math.BigInteger;

public class Disk {

	private String uuid;
   	private String name;
	private BigInteger freeMb;
	private double percentUsed;
	
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
	
	public BigInteger getFreeMb() {
		return freeMb;
	}
	
	public void setFreeMb(BigInteger freeMb) {
		this.freeMb = freeMb;
	}
	
	public double getPercentUsed() {
		return percentUsed;
	}
	
	public void setPercentUsed(double percentUsed) {
		this.percentUsed = percentUsed;
	}
}
