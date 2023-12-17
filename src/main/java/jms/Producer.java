package jms;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
    public static void main(String[] args) {
        try {
            // Établissement d'une connexion au broker ActiveMQ
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Création d'une session JMS
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // Commentaire: Une session JMS est créée avec une transaction automatique et une confirmation automatique.
            // Création de la destination (topic) pour l'envoi des messages
            Destination destination = session.createTopic("myTopic.topic");
            // Création d'un producteur de messages pour la destination spécifiée
            MessageProducer producer = session.createProducer(destination);
            // Configuration du mode de livraison du message (non persistant)
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Commentaire: Les messages ne seront pas stockés de manière persistante sur le broker.
            // Création d'un message texte
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Hello world!");
            // Envoi du message
            producer.send(textMessage);
            // Confirmation de la transaction
            session.commit();
            // Commentaire: La transaction est confirmée, ce qui signifie que le message est effectivement envoyé.
            System.out.println("Envoi du message...");
            // Fermeture de la session et de la connexion
            session.close();
            connection.close();

        } catch (Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
        }
    }
}
