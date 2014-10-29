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

public class Memory {

	private BigInteger wired;
	private BigInteger active;
	private BigInteger inactive;
	private BigInteger free;
	private BigInteger total;
	private BigInteger swapUsed;
	private BigInteger swapTotal;
	private BigInteger pageIns;
	private BigInteger pageOuts;
	
	public BigInteger getWired() {
		return wired;
	}

	public void setWired(BigInteger wired) {
		this.wired = wired;
	}

	public BigInteger getActive() {
		return active;
	}

	public void setActive(BigInteger active) {
		this.active = active;
	}

	public BigInteger getInactive() {
		return inactive;
	}

	public void setInactive(BigInteger inactive) {
		this.inactive = inactive;
	}

	public BigInteger getFree() {
		return free;
	}

	public void setFree(BigInteger free) {
		this.free = free;
	}

	public BigInteger getTotal() {
		return total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public BigInteger getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(BigInteger swapUsed) {
		this.swapUsed = swapUsed;
	}

	public BigInteger getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(BigInteger swapTotal) {
		this.swapTotal = swapTotal;
	}

	public BigInteger getPageIns() {
		return pageIns;
	}

	public void setPageIns(BigInteger pageIns) {
		this.pageIns = pageIns;
	}

	public BigInteger getPageOuts() {
		return pageOuts;
	}

	public void setPageOuts(BigInteger pageOuts) {
		this.pageOuts = pageOuts;
	}
}
