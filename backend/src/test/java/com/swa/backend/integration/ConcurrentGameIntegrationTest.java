package com.swa.backend.integration;

import com.swa.backend.model.Game;
import com.swa.backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentGameIntegrationTest {

    private GameService gameService;

    @BeforeEach
    void setup() {

        gameService = new GameService();
    }

    @Test
    void shouldHandleMultipleGamesIndependently() {

        Game game1 = gameService.createGame();
        Game game2 = gameService.createGame();

        String room1 = game1.getRoomId();
        String room2 = game2.getRoomId();

        gameService.makeMove(room1, 0, 0, "X");

        gameService.makeMove(room2, 1, 1, "X");

        Game updatedGame1 = gameService.getGame(room1);

        Game updatedGame2 = gameService.getGame(room2);

        assertEquals(
                "X",
                updatedGame1.getBoard()[0][0]
        );

        assertEquals(
                "",
                updatedGame1.getBoard()[1][1]
        );

        assertEquals(
                "X",
                updatedGame2.getBoard()[1][1]
        );

        assertEquals(
                "",
                updatedGame2.getBoard()[0][0]
        );
    }

    @Test
    void shouldStoreMultipleGamesSimultaneously() {

        for (int i = 0; i < 100; i++) {

            Game game = gameService.createGame();

            assertNotNull(game);

            assertNotNull(game.getRoomId());
        }
    }

    @Test
    void shouldAllowIndependentWinnersInDifferentGames() {

        Game game1 = gameService.createGame();
        Game game2 = gameService.createGame();

        String room1 = game1.getRoomId();
        String room2 = game2.getRoomId();

        // game 1 winner X
        gameService.makeMove(room1,0,0,"X");
        gameService.makeMove(room1,1,0,"O");

        gameService.makeMove(room1,0,1,"X");
        gameService.makeMove(room1,1,1,"O");

        gameService.makeMove(room1,0,2,"X");

        // game 2 still running
        gameService.makeMove(room2,2,2,"X");

        Game updatedGame1 = gameService.getGame(room1);

        Game updatedGame2 = gameService.getGame(room2);

        assertEquals(
                "X",
                updatedGame1.getWinner()
        );

        assertTrue(
                updatedGame1.isGameFinished()
        );

        assertFalse(
                updatedGame2.isGameFinished()
        );
    }
}
