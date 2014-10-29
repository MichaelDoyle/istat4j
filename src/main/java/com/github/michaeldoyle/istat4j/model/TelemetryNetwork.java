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
import java.util.Date;

public class TelemetryNetwork {
	
	private BigInteger id;
   	private BigInteger downloadBytes;
	private BigInteger uploadBytes;
	private Date time;
	
	public BigInteger getId() {
		return id;
	}
	
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public BigInteger getDownloadBytes() {
		return downloadBytes;
	}
	
	public void setDownloadBytes(BigInteger downloadBytes) {
		this.downloadBytes = downloadBytes;
	}
	
	public BigInteger getUploadBytes() {
		return uploadBytes;
	}
	
	public void setUploadBytes(BigInteger uploadBytes) {
		this.uploadBytes = uploadBytes;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
}
