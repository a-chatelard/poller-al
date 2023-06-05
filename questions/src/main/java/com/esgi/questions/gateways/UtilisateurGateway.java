package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

@Service
public class UtilisateurGateway {
    public boolean doesUserExist(Long utilisateurId){
        // Implémentation
        // HTTP GET /utilisateurs/{utilisateurId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
