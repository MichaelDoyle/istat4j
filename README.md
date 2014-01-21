# istat4j
A Java client library for querying the iStat Server, which provides the ability to remotely monitor your Mac, Windows PC or Linux server. CPU, Memory, Load and other statistics are available. Currently, only the [Linux](https://github.com/tiwilliam/istatd) implementation is supported.
The OSX and [Windows](https://github.com/bjango/iStat-Server-for-Windows) iStat Servers use a newer version of the protocol, which is not yet supported.

### Usage
```java
IstatService service = IstatServiceFactory.getService();
		
ServerConfiguration sc = new ServerConfiguration(
    ServerConfiguration.getLocalHostname(),
    ServerConfiguration.generateUuid(), 
    "host.domain", 5109, "12345");
		
service.setServerConfiguration(sc);

Telemetry telemetry = service.getAllTelemetry();
```

### Maven
```xml
<dependency>
  <groupId>com.github.michaeldoyle</groupId>
  <artifactId>istat4j</artifactId>
  <version>1.0.0</version>
</dependency>
```
