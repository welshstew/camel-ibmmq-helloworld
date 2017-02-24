package com.nullendpoint;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.jms.*;

/**
 * Created by swinchester on 24/02/2017.
 */
public class WMQProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        MQQueueConnectionFactory cf = new MQQueueConnectionFactory();
        cf.setHostName("mqdev-docker");
        cf.setPort(new Integer("1414"));
        cf.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        cf.setQueueManager("QM1");
        cf.setChannel("SYSTEM.DEF.SVRCONN");
        String user = "alice";
        String password = "passw0rd";
        cf.setStringProperty(WMQConstants.USERID, user);
        cf.setStringProperty(WMQConstants.PASSWORD, password);
        cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);

        QueueConnection queueConn = cf.createQueueConnection(user, password);
        QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueSender queueSender = queueSession.createSender(queueSession.createQueue("QName"));
        queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        TextMessage message = queueSession.createTextMessage("hello world");
        queueSender.send(message);
        queueConn.close();

    }
}
