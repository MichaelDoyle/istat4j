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

public class Memory {

	private long wired;
	private long active;
	private long inactive;
	private long free;
	private long total;
	private long swapUsed;
	private long swapTotal;
	private long pageIns;
	private long pageOuts;
	
	public long getWired() {
		return wired;
	}

	public void setWired(long wired) {
		this.wired = wired;
	}

	public long getActive() {
		return active;
	}

	public void setActive(long active) {
		this.active = active;
	}

	public long getInactive() {
		return inactive;
	}

	public void setInactive(long inactive) {
		this.inactive = inactive;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(long swapUsed) {
		this.swapUsed = swapUsed;
	}

	public long getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(long swapTotal) {
		this.swapTotal = swapTotal;
	}

	public long getPageIns() {
		return pageIns;
	}

	public void setPageIns(long pageIns) {
		this.pageIns = pageIns;
	}

	public long getPageOuts() {
		return pageOuts;
	}

	public void setPageOuts(long pageOuts) {
		this.pageOuts = pageOuts;
	}
}
