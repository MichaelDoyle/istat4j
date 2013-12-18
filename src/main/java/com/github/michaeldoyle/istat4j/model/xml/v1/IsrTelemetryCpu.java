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

@Root(strict=false, name="c")
public class IsrTelemetryCpu {

	@Attribute(name="id", required=true)
	private long id;
	
	@Attribute(name="u", required=true)
	private int user;
	
	@Attribute(name="s", required=true)
	private int system;
	
	@Attribute(name="n", required=true)
	private int nice;
	
	public IsrTelemetryCpu() {
		// necessary to keep xml deserialization happy
	}

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
}
