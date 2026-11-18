package com.portfolio.BudgetControl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks/whatsapp")
public class WhatsAppWebhookController {


    @Value("${WHATSAPP_VERIFY_TOKEN}")
    private String verifyToken;

    @GetMapping(produces = "text/plain")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && verifyToken.equals(token)){
            return ResponseEntity.ok(challenge);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        // Se mode for "subscribe" e token corresponder ao configurado:
        // retornar 200 com challenge no corpo.

        // Caso contrário:
        // retornar 403.
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("Teste webhook!");
    }


}
