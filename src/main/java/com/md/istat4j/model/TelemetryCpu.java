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
package com.md.istat4j.model;

public class TelemetryCpu {

	private long id;
	private int user;
	private int system;
	private int nice;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getUser() {
		return user;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getSystem() {
		return system;
	}
	
	public void setSystem(int system) {
		this.system = system;
	}
	
	public int getNice() {
		return nice;
	}
	
	public void setNice(int nice) {
		this.nice = nice;
	}
	
	public int getIdle() {
		return 100 - nice - system - user;
	}
}
