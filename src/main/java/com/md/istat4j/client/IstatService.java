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
package com.md.istat4j.client;

import com.md.istat4j.model.Telemetry;

/**
 * Provides methods for retrieving telemetry from the iStat daemon
 * including cpu, network, memory utilization. iStat collects utilization
 * data every second. A limitation of iStat currently, is that network
 * utilization is only collected for a single network interface.
 * 
 * @author michaeldoyle
 * 
 */
public interface IstatService {

	void setServerConfiguration(ServerConfiguration sc);
	
	/**
	 * Retrieves the "latest" (most recently collected) telemetry information
	 * from the iStat daemon.
	 * 
	 * @return The most recent telemetry information
	 */
	Telemetry getTelemetry() throws IstatException;
	
	/**
	 * Retrieves all telemetry information from the iStat daemon collected
	 * after the given server uptime.
	 * 
	 * @param since uptime in seconds 
	 * @return telemetry information collected after the given uptime
	 */
	Telemetry getTelemetry(long since) throws IstatException;
	
	/**
	 * Retrieves a baseline of all telemetry information from the iStat daemon.
	 * Typically this contains the last 5 minutes worth of data. Afterwards, 
	 * {@link #getTelemetry()} or {@link #getTelemetry(long)} should be 
	 * used to retrieve incremental updates.
	 * 
	 * @return all available telemetry information
	 */
	Telemetry getAllTelemetry() throws IstatException;
}
