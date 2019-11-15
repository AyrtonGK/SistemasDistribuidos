package ayrton.progA;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.jmx.Sensitive;

public class MessageProgA {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL is : tcp://localhost:61616"
	private static String subject1 = "solicitacalculo";// Queue Name.You can create any/many queue names as per your requirement.
	private static String subject2 = "resultadocalculo";

	public static void main(String[] args) throws JMSException {
		sender("10+10+10+10");
		receiver();
	}

	private static void sender(String conta) throws JMSException {
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating a non transactional session to send/receive JMS message.
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		// The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject1);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session.createTextMessage(conta);

		// Here we are sending our message!
		producer.send(message);

		System.out.println("JCG printing@@ '" + message.getText() + "'");

		connection.close();
	}

	private static void receiver() throws JMSException {
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating a non transactional session to send/receive JMS message.
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		// The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject2);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);

		// Here we receive the message.
		Message message = consumer.receive();

		// We will be using TestMessage in our example. MessageProducer sent us a
		// TextMessage
		// so we must cast to it to get access to its .getText() method.
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("Received message '" + textMessage.getText() + "'");
		}
		connection.close();
	}
}
