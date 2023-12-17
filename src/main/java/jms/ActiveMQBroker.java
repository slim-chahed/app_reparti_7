package jms;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQBroker {
    public static void main(String[] args) {

        try {
            // Création d'une instance du service de courtier ActiveMQ
            BrokerService brokerService = new BrokerService();

            // Configuration du service de courtier
            // brokerService.setPersistent(false);
            //Cette ligne désactive le stockage persistant, ce qui signifie que les messages ne seront pas sauvegardés sur le disque.

            // Ajout d'un connecteur pour le service de courtier ActiveMQ
            brokerService.addConnector("tcp://0.0.0.0:61616");
            // Un connecteur est ajouté pour permettre la communication avec le courtier via le protocole TCP sur le port 61616.

            // Démarrage du service de courtier ActiveMQ
            brokerService.start();
            //Le service de courtier est démarré, ce qui rend le courtier actif et prêt à traiter les messages.

        } catch (Exception e) {
            // Gestion des exceptions
            throw new RuntimeException(e);
        }
    }
}
