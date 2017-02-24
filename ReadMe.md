# camel-ibmmq-helloworld

Just a quickstart to attempt to get connectivity with an IBM MQ.  All of this is hardcoded in the src/main/resources/META-INF.spring/camel-context.xml.

This is made to connect to the IBM MQ docker image as per [https://github.com/welshstew/mqdev-docker](https://github.com/welshstew/mqdev-docker)

## Run on Openshift
```
oc new-app java-fis-openshift:1.0~https://github.com/welshstew/camel-ibmmq-helloworld
```

### Current Problems

```
2017-02-24 18:31:11,221 [1 - timer://foo] WARN  TimerConsumer                  - Error processing exchange. Exchange[Message: hello WMQ]. Caused by: [org.springframework.jms.JmsSecurityException - JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.; nested exception is com.ibm.msg.client.jms.DetailedJMSSecurityException: JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.
Please check if the supplied username and password are correct on the QueueManager to which you are connecting.; nested exception is com.ibm.mq.MQException: JMSCMQ0001: IBM MQ call failed with compcode '2' ('MQCC_FAILED') reason '2035' ('MQRC_NOT_AUTHORIZED').]
org.springframework.jms.JmsSecurityException: JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.; nested exception is com.ibm.msg.client.jms.DetailedJMSSecurityException: JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.
Please check if the supplied username and password are correct on the QueueManager to which you are connecting.; nested exception is com.ibm.mq.MQException: JMSCMQ0001: IBM MQ call failed with compcode '2' ('MQCC_FAILED') reason '2035' ('MQRC_NOT_AUTHORIZED').
	at org.springframework.jms.support.JmsUtils.convertJmsAccessException(JmsUtils.java:291)
	at org.springframework.jms.support.JmsAccessor.convertJmsAccessException(JmsAccessor.java:168)
	at org.springframework.jms.core.JmsTemplate.execute(JmsTemplate.java:469)
	at org.apache.camel.component.jms.JmsConfiguration$CamelJmsTemplate.send(JmsConfiguration.java:235)
	at org.apache.camel.component.jms.JmsProducer.doSend(JmsProducer.java:413)
	at org.apache.camel.component.jms.JmsProducer.processInOnly(JmsProducer.java:367)
	at org.apache.camel.component.jms.JmsProducer.process(JmsProducer.java:153)
	at org.apache.camel.processor.SendProcessor.process(SendProcessor.java:139)
	at org.apache.camel.management.InstrumentationProcessor.process(InstrumentationProcessor.java:77)
	at org.apache.camel.processor.RedeliveryErrorHandler.process(RedeliveryErrorHandler.java:448)
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:121)
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:83)
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
	at org.apache.camel.component.timer.TimerConsumer.sendTimerExchange(TimerConsumer.java:165)
	at org.apache.camel.component.timer.TimerConsumer$1.run(TimerConsumer.java:73)
	at java.util.TimerThread.mainLoop(Timer.java:555)
	at java.util.TimerThread.run(Timer.java:505)
Caused by: com.ibm.msg.client.jms.DetailedJMSSecurityException: JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.
Please check if the supplied username and password are correct on the QueueManager to which you are connecting.
	at com.ibm.msg.client.wmq.common.internal.Reason.reasonToException(Reason.java:531)
	at com.ibm.msg.client.wmq.common.internal.Reason.createException(Reason.java:215)
	at com.ibm.msg.client.wmq.internal.WMQConnection.<init>(WMQConnection.java:422)
	at com.ibm.msg.client.wmq.factories.WMQConnectionFactory.createV7ProviderConnection(WMQConnectionFactory.java:8475)
	at com.ibm.msg.client.wmq.factories.WMQConnectionFactory.createProviderConnection(WMQConnectionFactory.java:7814)
	at com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl._createConnection(JmsConnectionFactoryImpl.java:299)
	at com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl.createConnection(JmsConnectionFactoryImpl.java:236)
	at com.ibm.mq.jms.MQConnectionFactory.createCommonConnection(MQConnectionFactory.java:6024)
	at com.ibm.mq.jms.MQConnectionFactory.createConnection(MQConnectionFactory.java:6073)
	at org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter.doCreateConnection(UserCredentialsConnectionFactoryAdapter.java:175)
	at org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter.createConnection(UserCredentialsConnectionFactoryAdapter.java:150)
	at org.springframework.jms.support.JmsAccessor.createConnection(JmsAccessor.java:184)
	at org.springframework.jms.core.JmsTemplate.execute(JmsTemplate.java:456)
	... 15 more
Caused by: com.ibm.mq.MQException: JMSCMQ0001: IBM MQ call failed with compcode '2' ('MQCC_FAILED') reason '2035' ('MQRC_NOT_AUTHORIZED').
	at com.ibm.msg.client.wmq.common.internal.Reason.createException(Reason.java:203)
	... 26 more
2017-02-24 18:31:15,853 [Consumer[hello]] ERROR ultJmsMessageListenerContainer - Could not refresh JMS Connection for destination 'hello' - retrying in 5000 ms. Cause: JMSWMQ2013: The security authentication was not valid that was supplied for QueueManager 'MQ1' with connection mode 'Client' and host name 'mqdev-docker(1414)'.; nested exception is com.ibm.mq.MQException: JMSCMQ0001: IBM MQ call failed with compcode '2' ('MQCC_FAILED') reason '2035' ('MQRC_NOT_AUTHORIZED').
```