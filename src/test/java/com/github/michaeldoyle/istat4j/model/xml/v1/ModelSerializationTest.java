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

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import com.github.michaeldoyle.istat4j.model.xml.v1.IsrConnectionTest;
import com.github.michaeldoyle.istat4j.model.xml.v1.IsrConnectionTestRequest;
import com.github.michaeldoyle.istat4j.model.xml.v1.IsrRegistrationRequest;
import com.github.michaeldoyle.istat4j.model.xml.v1.IsrTelemetryRequest;

public class ModelSerializationTest {

	private static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	private static final String REQUEST_CONNTEST = PROLOG
			+ "\n<isr><conntest/></isr>";
	
	private static final String REQUEST_REGISTER = PROLOG
			+ "\n<isr><h>hostname</h><duuid>123456789</duuid></isr>";
	
	private static final String REQUEST_TELEMETRY = PROLOG
			+ "\n<isr><rid>1</rid><c>-1</c><n>-1</n><m>-1</m><lo>-1</lo><t>-1</t><f>-1</f><u>-1</u><d>-1</d></isr>";
	
	private static Serializer serializer;

	@BeforeClass
	public static void setUp() {
		Format format = new Format(0, PROLOG);
		serializer = new Persister(format);
	}
	
	@Test
	public void testSerializeConnectionTestRequest() throws Exception {	
		IsrConnectionTestRequest request = new IsrConnectionTestRequest();
		request.setConnTest(new IsrConnectionTest());
		
		StringWriter writer = new StringWriter();
		serializer.write(request, writer);
		
		assertEquals(REQUEST_CONNTEST, writer.toString());
	}
	
	@Test
	public void testSerializeRegistrationRequest() throws Exception {	
		IsrRegistrationRequest request = new IsrRegistrationRequest();
		request.setHostname("hostname");
		request.setUuid("123456789");
		
		StringWriter writer = new StringWriter();
		serializer.write(request, writer);
		
		assertEquals(REQUEST_REGISTER, writer.toString());
	}
	
	@Test
	public void testSerializeTelemetryRequest() throws Exception {	
		IsrTelemetryRequest request = new IsrTelemetryRequest();
		request.setCpu(-1);
		request.setDisk(-1);
		request.setFan(-1);
		request.setLoad(-1);
		request.setMemory(-1);
		request.setNetwork(-1);
		request.setRequestId(1);
		request.setTemp(-1);
		request.setUptime(-1);
		
		StringWriter writer = new StringWriter();
		serializer.write(request, writer);
		
		assertEquals(REQUEST_TELEMETRY, writer.toString());
	}
}
