package com.swa.backend.integration;

import com.swa.backend.model.Game;
import com.swa.backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerFlowIntegrationTest {

    private GameService gameService;

    private String roomId;

    @BeforeEach
    void setup() {

        gameService = new GameService();

        Game game = gameService.createGame();

        roomId = game.getRoomId();

        gameService.joinGame(roomId);
    }

    @Test
    void shouldPlayCompleteGame() {

        gameService.makeMove(roomId, 0, 0, "X");
        gameService.makeMove(roomId, 1, 0, "O");

        gameService.makeMove(roomId, 0, 1, "X");
        gameService.makeMove(roomId, 1, 1, "O");

        gameService.makeMove(roomId, 0, 2, "X");

        Game game = gameService.getGame(roomId);

        assertTrue(game.isGameFinished());

        assertEquals(
                "X",
                game.getWinner()
        );
    }

    @Test
    void shouldPlayDrawGame() {

        gameService.makeMove(roomId,0,0,"X");
        gameService.makeMove(roomId,0,1,"O");

        gameService.makeMove(roomId,0,2,"X");
        gameService.makeMove(roomId,1,1,"O");

        gameService.makeMove(roomId,1,0,"X");
        gameService.makeMove(roomId,1,2,"O");

        gameService.makeMove(roomId,2,1,"X");
        gameService.makeMove(roomId,2,0,"O");

        gameService.makeMove(roomId,2,2,"X");

        Game game = gameService.getGame(roomId);

        assertTrue(game.isGameFinished());

        assertEquals(
                "DRAW",
                game.getWinner()
        );
    }

    @Test
    void shouldPreventMovesAfterGameFinished() {

        gameService.makeMove(roomId, 0, 0, "X");
        gameService.makeMove(roomId, 1, 0, "O");

        gameService.makeMove(roomId, 0, 1, "X");
        gameService.makeMove(roomId, 1, 1, "O");

        gameService.makeMove(roomId, 0, 2, "X");

        gameService.makeMove(roomId, 2, 2, "O");

        Game game = gameService.getGame(roomId);

        assertEquals(
                "",
                game.getBoard()[2][2]
        );
    }
}
