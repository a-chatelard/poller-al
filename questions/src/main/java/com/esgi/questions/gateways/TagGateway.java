package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

/**
 * Gateway d'accès à l'API de gestion des tags.
 */
@Service
public class TagGateway {
    /**
     * Indique si un tag existe ou non.
     * @param tagId identifiant du tag.
     * @return True si le tag existe, False sinon.
     */
    public boolean doesTagExist(Long tagId){
        // Implémentation
        // HTTP GET /tags/{tagId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
