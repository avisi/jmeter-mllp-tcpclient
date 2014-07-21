jmeter-mllp-tcpclient
=====================

TCPClient for use in JMeter TCP sampler for sending messages over MLLP to a HL7 interface engine

### Getting started

- Clone the repo
- Build using maven `mvn install`
- Copy the resulting JAR file to JMeter lib directory `cp target/mllptcpsampler-1.0-SNAPSHOT.jar <jmeter_home>/lib/mllptcpsampler-1.0-SNAPSHOT.jar`
- Start JMeter
- Open the [example testplan](https://github.com/avisi/jmeter-mllp-tcpclient/blob/master/example/example_mllp_testplan.jmx) in JMeter or follow the paragragh below to create your own testplan

### Creating a testplan

- Create a Thread Group
- Add a regular TCP Sampler to the Thread Group 
- Specify `nl.avisi.jmeter.mllpsampler.MllpTCPClient` as the TCP client classname in the TCP Sampler.
- Enter a hostname and portnumber in the TCP Sampler, add a HL7 text message, set no delay
- Fire away!

![](https://github.com/avisi/jmeter-mllp-tcpclient/blob/master/example/jmeter_screenshot.png)

