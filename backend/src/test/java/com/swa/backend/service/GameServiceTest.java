package com.swa.backend.service;

import com.swa.backend.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    private GameService gameService;

    @BeforeEach
    void setup(){
        gameService = new GameService();
    }
    @Test
    void shouldCreateGameSuccessfully(){
        Game game = gameService.createGame();
        assertNotNull(game);
        assertNotNull(game.getRoomId());
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(1, game.getPlayerCount());
    }

    @Test
    void shouldJoinGameSuccessfully(){
        Game game = gameService.createGame();
        Game joinedGame = gameService.joinGame(game.getRoomId());
        assertNotNull(joinedGame);
        assertEquals(2, joinedGame.getPlayerCount());
    }
    @Test
    void shouldReturnNullForInvalidRoom(){
        Game game = gameService.joinGame("Invalid");
        assertNull(game);
    }
    @Test
    void shouldMakeMoveSuccessfully(){
        Game game = gameService.createGame();
        gameService.makeMove(
                game.getRoomId(),
                0,
                0,
                "X"
        );
        Game updatedGame = gameService.getGame(game.getRoomId());
        assertEquals(
                "X",
                updatedGame.getBoard()[0][0]
        );
    }
    @Test
    void shouldSwitchTurns(){
        Game game = gameService.createGame();
        gameService.makeMove(
                game.getRoomId(),
                0,
                0,
                "X"
        );
        Game updatedGame = gameService.getGame(game.getRoomId());
        assertEquals("O", updatedGame.getCurrentPlayer());
    }
    @Test
    void shouldDetectWinner(){
        Game game = gameService.createGame();
        String roomId = game.getRoomId();
        gameService.makeMove(roomId, 0, 0, "X");
        gameService.makeMove(roomId, 1, 0, "O");
        gameService.makeMove(roomId, 0, 1, "X");
        gameService.makeMove(roomId, 1, 1, "O");
        gameService.makeMove(roomId,0, 2, "X");

        Game updatedGame = gameService.getGame(roomId);
        assertTrue(updatedGame.isGameFinished());
        assertEquals("X", updatedGame.getWinner());
    }
    @Test
    void shouldDetectDraw() {

        Game game = gameService.createGame();

        String roomId = game.getRoomId();

        gameService.makeMove(roomId,0,0,"X");
        gameService.makeMove(roomId,0,1,"O");

        gameService.makeMove(roomId,0,2,"X");
        gameService.makeMove(roomId,1,1,"O");

        gameService.makeMove(roomId,1,0,"X");
        gameService.makeMove(roomId,1,2,"O");

        gameService.makeMove(roomId,2,1,"X");
        gameService.makeMove(roomId,2,0,"O");

        gameService.makeMove(roomId,2,2,"X");

        Game updatedGame = gameService.getGame(roomId);

        assertTrue(updatedGame.isGameFinished());

        assertEquals("DRAW", updatedGame.getWinner());
    }
}
