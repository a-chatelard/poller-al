package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

/**
 * Gateway d'accès à l'API de gestion des utilisateurs.
 */
@Service
public class UtilisateurGateway {
    /**
     * Indique si un utilisateur existe ou non.
     * @param utilisateurId identifiant de l'utilisateur.
     * @return True si l'utilisateur existe, False sinon.
     */
    public boolean doesUserExist(Long utilisateurId){
        // Implémentation
        // HTTP GET /utilisateurs/{utilisateurId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
