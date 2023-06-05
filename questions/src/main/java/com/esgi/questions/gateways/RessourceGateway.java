package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

@Service
public class RessourceGateway {
    public boolean doesRessourceExist(Long ressourceId){
        // Implémentation
        // HTTP GET /ressources/{ressourceId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
