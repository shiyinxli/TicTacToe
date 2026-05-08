package com.swa.backend.service;

import com.swa.backend.model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class GameService {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Game createGame(){
        String roomId = UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
        Game game = new Game();
        game.setRoomId(roomId);
        game.setPlayerCount(1);

        games.put(roomId, game);
        return game;
    }

    public Game getGame(String roomId){
        return games.get(roomId);
    }

    public Game joinGame(String roomId){
        Game game = games.get(roomId);

        if(game == null){
            return null;
        }

        if(game.getPlayerCount() >= 2){
            return null;
        }

        game.setPlayerCount(2);

        return game;
    }
}
