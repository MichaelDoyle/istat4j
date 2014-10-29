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
package com.github.michaeldoyle.istat4j.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Container for all information required by the iStat service.
 * 
 * @author michaeldoyle
 */
public class ServerConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ServerConfiguration.class);
	
	private String clientHostname;
	private String clientUuid;
	private String serverHostname;
	private int serverPort;
	private String serverPassword;

	public ServerConfiguration(String clientHostname, String clientUuid,
			String serverHostname, int serverPort, String serverPassword) {
		this.clientHostname = clientHostname;
		this.clientUuid = clientUuid;
		this.serverHostname = serverHostname;
		this.serverPort = serverPort;
		this.serverPassword = serverPassword;
	}

	public String getClientHostname() {
		return clientHostname;
	}

	public void setClientHostname(String clientHostname) {
		this.clientHostname = clientHostname;
	}

	public String getClientUuid() {
		return clientUuid;
	}

	public void setClientUuid(String clientUuid) {
		this.clientUuid = clientUuid;
	}

	public String getServerHostname() {
		return serverHostname;
	}

	public void setServerHostname(String serverHostname) {
		this.serverHostname = serverHostname;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}
	
	public void validate() throws IllegalArgumentException {
		if (nullOrEmpty(clientHostname)) {
			throw new IllegalArgumentException("Client hostname is required.");
		}
		
		if (nullOrEmpty(clientUuid)) {
			throw new IllegalArgumentException("Client UUID is required.");	
		}
		
		if (nullOrEmpty(serverHostname)) {
			throw new IllegalArgumentException("Server hostname is required.");
		}
		
		if (nullOrEmpty(serverPassword)) {
			throw new IllegalArgumentException("Server password is required.");
		}
		
		if (serverPort <= 0) {
			throw new IllegalArgumentException("Server port must be >= 0.");
		}
	}
	
	private boolean nullOrEmpty(String s) {
		return s == null || "".equals(s);
	}
	
	/**
	 * Static helper to get the canonical hostname for the client
	 * @return canonical hostname for the local host
	 */
	public static String getLocalHostname() {
		String hostname = "";
		
		try {
			hostname = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			logger.info("Exception geting local hostname.", e);
		}
		
		return hostname;
	}
	
	/**
	 * Static helper to generate a UUID for the client
	 * 
	 * @return a pseudo randomly generated UUID
	 */
	public static String generateUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
