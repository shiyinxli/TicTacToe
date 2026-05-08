package com.swa.backend.controller;

import com.swa.backend.model.Game;
import com.swa.backend.model.Move;
import com.swa.backend.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameWebSocketController {
    private final GameService gameService;

    private final SimpMessagingTemplate messagingTemplate;
    public GameWebSocketController(GameService gameService, SimpMessagingTemplate messagingTemplate){
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/move")
    public void handleMove(Move move){
        Game updatedGame = gameService.makeMove(
                move.getRoomId(),
                move.getRow(),
                move.getCol(),
                move.getPlayer()
        );

        messagingTemplate.convertAndSend(
                "/topic/game/" + move.getRoomId(),
                updatedGame
        );
    }
}
