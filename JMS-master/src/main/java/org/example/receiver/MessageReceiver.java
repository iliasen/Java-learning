package org.example.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MessageReceiver {

    private static final String BROKER_URL = "tcp://localhost:61616";

    public void receiveMessage(String queueName) {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);

            // Слушаем сообщения
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                System.out.println("Received message: " + text);


                try (BufferedWriter writer = new BufferedWriter(new FileWriter("message_word_count.txt", true))) {
                    writer.write("Message: \"" + text + "\n");
                    System.out.println("Word count written to file.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection manually
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
