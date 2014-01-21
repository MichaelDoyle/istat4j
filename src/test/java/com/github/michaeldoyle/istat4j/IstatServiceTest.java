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
package com.github.michaeldoyle.istat4j;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.michaeldoyle.istat4j.client.IstatException;
import com.github.michaeldoyle.istat4j.client.IstatService;
import com.github.michaeldoyle.istat4j.client.IstatServiceFactory;
import com.github.michaeldoyle.istat4j.client.IstatUnauthorizedException;
import com.github.michaeldoyle.istat4j.client.ServerConfiguration;
import com.github.michaeldoyle.istat4j.model.Telemetry;

public class IstatServiceTest {

	private static IstatService service;

	@BeforeClass
	public static void setUp() throws FileNotFoundException, IOException {
		service = IstatServiceFactory.getService();

		Properties properties = new Properties() ;
		URL url =  ClassLoader.getSystemResource("istat4j.properties");
		properties.load(new FileInputStream(new File(url.getFile())));
		
		String hostname = properties.getProperty("istat4j.server.hostname");
		int port = Integer.parseInt(properties.getProperty("istat4j.server.port"));
		String password = properties.getProperty("istat4j.server.password");
		
		ServerConfiguration sc = new ServerConfiguration(
				ServerConfiguration.getLocalHostname(),
				ServerConfiguration.generateUuid(), 
				hostname, port, password);
		
		service.setServerConfiguration(sc);
	}
	
	@Test
	public void testGetAllTelemetry() throws IstatException, IstatUnauthorizedException {	
		Telemetry telemetry = service.getAllTelemetry();
		assertTrue(telemetry.getCpuTelemetry().size() >= 1);
		assertTrue(telemetry.getNetworkTelemetry().size() >= 1);
	}
	
	@Test
	public void testGetTelemetry() throws IstatException, IstatUnauthorizedException {	
		Telemetry telemetry = service.getTelemetry();
		assertTrue(telemetry.getCpuTelemetry().size() == 1);
		assertTrue(telemetry.getNetworkTelemetry().size() == 1);
	}
	
	@Test
	public void testGetTelemetry_since() throws IstatException, IstatUnauthorizedException {	
		Telemetry telemetry = service.getTelemetry(-1);
		assertTrue(telemetry.getCpuTelemetry().size() == 1);
		assertTrue(telemetry.getNetworkTelemetry().size() == 1);
	}
	
	@Test(expected=IstatUnauthorizedException.class)
	public void testIstatUnauthorizedException_wrongPasscode()
			throws IstatException, IstatUnauthorizedException,
			FileNotFoundException, IOException {
		
		IstatService service = IstatServiceFactory.getService();

		Properties properties = new Properties() ;
		URL url =  ClassLoader.getSystemResource("istat4j.properties");
		properties.load(new FileInputStream(new File(url.getFile())));
		
		String hostname = properties.getProperty("istat4j.server.hostname");
		int port = Integer.parseInt(properties.getProperty("istat4j.server.port"));
		
		ServerConfiguration sc = new ServerConfiguration(
				ServerConfiguration.getLocalHostname(),
				ServerConfiguration.generateUuid(), 
				hostname, port, "wrong");
		
		service.setServerConfiguration(sc);
		
		service.getAllTelemetry();
	}
	
	@Test(expected=IstatException.class)
	public void testIstatException_badHostname() throws IstatException,
			IstatUnauthorizedException, FileNotFoundException, IOException {
		
		IstatService service = IstatServiceFactory.getService();

		ServerConfiguration sc = new ServerConfiguration(
				ServerConfiguration.getLocalHostname(),
				ServerConfiguration.generateUuid(), 
				"wrong", 5109, "n/a");
		
		service.setServerConfiguration(sc);
		
		service.getAllTelemetry();
	}
	
	@Test(expected=IstatException.class)
	public void testIstatException_badPort() throws IstatException,
			IstatUnauthorizedException, FileNotFoundException, IOException {
		
		IstatService service = IstatServiceFactory.getService();
		
		Properties properties = new Properties() ;
		URL url =  ClassLoader.getSystemResource("istat4j.properties");
		properties.load(new FileInputStream(new File(url.getFile())));
		
		String hostname = properties.getProperty("istat4j.server.hostname");
		int port = Integer.parseInt(properties.getProperty("istat4j.server.port"));
		String password = properties.getProperty("istat4j.server.password");
		
		ServerConfiguration sc = new ServerConfiguration(
				ServerConfiguration.getLocalHostname(),
				ServerConfiguration.generateUuid(), 
				hostname, 5100, password);
		
		service.setServerConfiguration(sc);
		
		service.getAllTelemetry();
	}
}
