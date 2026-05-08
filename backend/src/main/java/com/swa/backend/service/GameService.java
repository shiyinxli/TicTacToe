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

    public Game makeMove(String roomId, int row, int col, String player){
        Game game = games.get(roomId);
        if(game == null){
            return null;
        }
        if(game.isGameFinished()){
            return game;
        }
        if(!game.getCurrentPlayer().equals(player)){
            return game;
        }
        //check empty cell
        if(!game.getBoard()[row][col].equals("")){
            return game;
        }
        game.getBoard()[row][col] = player;

        if(player.equals("X")){
            game.setCurrentPlayer("O");
        }else{
            game.setCurrentPlayer("X");
        }
        checkWinner(game);
        return game;
    }

    private void checkWinner(Game game) {

        String[][] b = game.getBoard();

        // Rows
        for (int i = 0; i < 3; i++) {

            if (!b[i][0].equals("") &&
                    b[i][0].equals(b[i][1]) &&
                    b[i][1].equals(b[i][2])) {

                game.setWinner(b[i][0]);
                game.setGameFinished(true);
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {

            if (!b[0][i].equals("") &&
                    b[0][i].equals(b[1][i]) &&
                    b[1][i].equals(b[2][i])) {

                game.setWinner(b[0][i]);
                game.setGameFinished(true);
            }
        }

        // Diagonal
        if (!b[0][0].equals("") &&
                b[0][0].equals(b[1][1]) &&
                b[1][1].equals(b[2][2])) {

            game.setWinner(b[0][0]);
            game.setGameFinished(true);
        }

        // Other diagonal
        if (!b[0][2].equals("") &&
                b[0][2].equals(b[1][1]) &&
                b[1][1].equals(b[2][0])) {

            game.setWinner(b[0][2]);
            game.setGameFinished(true);
        }

        // Check draw
        boolean boardFull = true;

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (b[row][col].equals("")) {
                    boardFull = false;
                }
            }
        }

        if (boardFull && !game.isGameFinished()) {

            game.setWinner("DRAW");

            game.setGameFinished(true);
        }
    }
}
