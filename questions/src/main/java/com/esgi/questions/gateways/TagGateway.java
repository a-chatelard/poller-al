package com.esgi.questions.gateways;

import org.springframework.stereotype.Service;

@Service
public class TagGateway {
    public boolean doesTagExist(Long tagId){
        // Implémentation
        // HTTP GET /tags/{tagId}
        // Si 200 => True, si 404 => False
        // ou
        // GRPC via service dédié
        return true;
    }
}
