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
package com.github.michaeldoyle.istat4j.model;

import java.util.Date;

public class TelemetryNetwork {
	
	private long id;
   	private long downloadKb;
	private long uploadKb;
	private Date time;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getDownloadKb() {
		return downloadKb;
	}
	
	public void setDownloadKb(long downloadKb) {
		this.downloadKb = downloadKb;
	}
	
	public long getUploadKb() {
		return uploadKb;
	}
	
	public void setUploadKb(long uploadKb) {
		this.uploadKb = uploadKb;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
}
