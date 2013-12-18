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

public class Load {

	private double oneMinuteAverage;
	private double fiveMinuteAverage;
	private double fifteenMinuteAverage;

	public double getOneMinuteAverage() {
		return oneMinuteAverage;
	}

	public void setOneMinuteAverage(double oneMinuteAverage) {
		this.oneMinuteAverage = oneMinuteAverage;
	}

	public double getFiveMinuteAverage() {
		return fiveMinuteAverage;
	}

	public void setFiveMinuteAverage(double fiveMinuteAverage) {
		this.fiveMinuteAverage = fiveMinuteAverage;
	}

	public double getFifteenMinuteAverage() {
		return fifteenMinuteAverage;
	}

	public void setFifteenMinuteAverage(double fifteenMinuteAverage) {
		this.fifteenMinuteAverage = fifteenMinuteAverage;
	}
}
