package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

/**
 * Gateway d'accès à l'API de gestion des ressources.
 */
@Service
public class RessourceGateway {
    /**
     * Indique si une ressource existe ou non.
     * @param ressourceId identifiant de la ressource.
     * @return True si la ressource existe, False sinon.
     */
    public boolean doesRessourceExist(Long ressourceId){
        // Implémentation
        // HTTP GET /ressources/{ressourceId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
