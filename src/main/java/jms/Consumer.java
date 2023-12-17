package jms;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
    public static void main(String[] args) {
        try {
            // Établissement d'une connexion au broker ActiveMQ
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Création d'une session JMS
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // Commentaire: Une session JMS est créée avec une transaction automatique et une confirmation automatique.

            // Création de la destination (topic) pour la réception des messages
            Destination destination = session.createTopic("myTopic.topic");

            // Création d'un consommateur de messages pour la destination spécifiée
            MessageConsumer consumer = session.createConsumer(destination);

            // Mise en place d'un écouteur de messages
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        // Traitement des messages texte
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("Message reçu : " + textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        } catch (Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
        }
    }
}
