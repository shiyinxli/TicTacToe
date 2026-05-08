package com.swa.backend.controller;

import com.swa.backend.model.Game;
import com.swa.backend.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin("*")
public class GameRestController {
    private final GameService gameService;

    public GameRestController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public Game createGame(){
        return gameService.createGame();
    }

    @PostMapping("/join/{roomId}")
    public ResponseEntity<Game> joinGame(@PathVariable String roomId){
        Game game = gameService.joinGame(roomId);
        if(game == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
}
