package ayrton.progB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class MessageProgB {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
	// on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL is : tcp://localhost:61616"
	private static String subject1 = "resultadocalculo";// Queue Name.You can create any/many queue names as per your
														// requirement.
	private static String subject2 = "solicitacalculo";

	public static void main(String[] args) throws JMSException {
		// String teste =calcula("10+10+10");
		// System.out.println(teste);
		sender(calcula(receiver()));
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

	private static String receiver() throws JMSException {
		// Getting JMS connection from the server and starting it
		String texto = "";
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
			texto = textMessage.getText();
			System.out.println("Received message '" + textMessage.getText() + "'");
		}
		connection.close();
		return texto;
	}

	public static String calcula(String calcular) {
		int valor = 0, contador = 0;
		int qtdDOperacoes = 0;
		String resultado = "";
		// Specifies the string pattern which is to be searched
		Pattern pattern = Pattern.compile("\\+");

		Matcher m = pattern.matcher(calcular.toString());

		while (m.find()) {
			if (m.find(m.start())) {
				qtdDOperacoes++;
				// System.out.println(qtdDOperacoes);
			}

		}
		int valores[] = new int[qtdDOperacoes + 1];
		// Used to perform case insensitive search of the string
		String[] result = pattern.split(calcular);

		for (String temp : result) {
			valor = Integer.parseInt(temp);
			valores[contador] = valor;
			contador++;
		}
		valor = 0;
		for (contador = 0; contador < valores.length; contador++) {
			valor = valor + valores[contador];
		}

		resultado = Integer.toString(valor);

		return resultado;
	}
}