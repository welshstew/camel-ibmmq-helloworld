# camel-ibmmq-helloworld

Just a quickstart to attempt to get connectivity with an IBM MQ.  All of this is hardcoded in the src/main/resources/META-INF.spring/camel-context.xml.

This is made to connect to the IBM MQ docker image as per [https://github.com/welshstew/mqdev-docker](https://github.com/welshstew/mqdev-docker)

## Run on Openshift
```
oc new-app fis-java-openshift:1.0~https://github.com/welshstew/camel-ibmmq-helloworld -e MQ_HOST=mqdev-docker,MQ_PORT=1414,MQ_CHANNEL=PASSWORD.SVRCONN,MQ_USERNAME=alice,MQ_PASSWORD=passw0rd,MQ_QUEUE_MANAGER=QM1
```
### Log Output

```
Picked up JAVA_TOOL_OPTIONS: -Duser.home=/home/jboss -Duser.name=jboss
I> No access restrictor found, access to all MBean is allowed
Jolokia: Agent started with URL https://172.17.0.13:8778/jolokia/
2017-02-27 09:56:03,246 [main           ] INFO  MainSupport                    - Apache Camel 2.15.1.redhat-621084 starting
2017-02-27 09:56:03,312 [main           ] INFO  ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@783e6358: startup date [Mon Feb 27 09:56:03 UTC 2017]; root of context hierarchy
2017-02-27 09:56:03,360 [main           ] INFO  XmlBeanDefinitionReader        - Loading XML bean definitions from class path resource [META-INF/spring/camel-context.xml]
2017-02-27 09:56:04,425 [main           ] INFO  DefaultListableBeanFactory     - Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@64b0598: defining beans [wmqProcessor,propertyPlaceholderConfigurer,template,consumerTemplate,camel-1:beanPostProcessor,camel-1,wmq,wMQConfig,wMQConnectionFactory,connectionFactory]; root of factory hierarchy
2017-02-27 09:56:05,075 [main           ] INFO  SpringCamelContext             - Apache Camel 2.15.1.redhat-621084 (CamelContext: camel-1) is starting
2017-02-27 09:56:05,077 [main           ] INFO  ManagedManagementStrategy      - JMX is enabled
2017-02-27 09:56:05,205 [main           ] INFO  DefaultTypeConverter           - Loaded 183 type converters
2017-02-27 09:56:05,333 [main           ] INFO  SpringCamelContext             - AllowUseOriginalMessage is enabled. If access to the original message is not needed, then its recommended to turn this option off as it may improve performance.
2017-02-27 09:56:05,333 [main           ] INFO  SpringCamelContext             - StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2017-02-27 09:56:05,391 [main           ] INFO  SpringCamelContext             - Route: publish-processor-route started and consuming from: Endpoint[timer://fooone?period=5000]
2017-02-27 09:56:05,393 [main           ] INFO  SpringCamelContext             - Route: publish-spring-route started and consuming from: Endpoint[timer://footwo?period=10000]
2017-02-27 09:56:05,553 [main           ] INFO  SpringCamelContext             - Route: consume-route started and consuming from: Endpoint[wmq://queue:QName]
2017-02-27 09:56:05,557 [main           ] INFO  SpringCamelContext             - Total 3 routes, of which 3 is started.
2017-02-27 09:56:05,558 [main           ] INFO  SpringCamelContext             - Apache Camel 2.15.1.redhat-621084 (CamelContext: camel-1) started in 0.482 seconds
2017-02-27 09:56:06,723 [Consumer[QName]] INFO  consume-route                  - hello world
2017-02-27 09:56:06,754 [Consumer[QName]] INFO  consume-route                  - hello WMQ
2017-02-27 09:56:11,609 [Consumer[QName]] INFO  consume-route                  - hello world
2017-02-27 09:56:16,618 [Consumer[QName]] INFO  consume-route                  - hello WMQ
2017-02-27 09:56:16,646 [Consumer[QName]] INFO  consume-route                  - hello world
```

## Deploy

Example command to deploy the .tar.gz file (to my local/vagrant nexus)

```
mvn deploy:deploy-file  -DgroupId=com.nullendpoint  -DartifactId=camel-ibmmq-helloworld  -Dversion=1.0  -Dpackaging=tar.gz  -Dfile=/Users/swinchester/GitHub/camel-ibmmq-helloworld/target/camel-ibmmq-helloworld-1.0-SNAPSHOT-app.tar.gz -DrepositoryId=nexus -Durl=http://10.1.2.4:8081/nexus/content/repositories/releases/
```

