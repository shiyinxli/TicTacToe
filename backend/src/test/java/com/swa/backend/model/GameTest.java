package com.swa.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldInitializeEmptyBoard() {

        Game game = new Game();

        String[][] board = game.getBoard();

        assertEquals("", board[0][0]);
        assertEquals("", board[0][1]);
        assertEquals("", board[0][2]);

        assertEquals("", board[1][0]);
        assertEquals("", board[1][1]);
        assertEquals("", board[1][2]);

        assertEquals("", board[2][0]);
        assertEquals("", board[2][1]);
        assertEquals("", board[2][2]);
    }

    @Test
    void shouldDefaultCurrentPlayerToX() {

        Game game = new Game();

        assertEquals(
                "X",
                game.getCurrentPlayer()
        );
    }

    @Test
    void shouldInitializeWinnerAsEmpty() {

        Game game = new Game();

        assertEquals(
                "",
                game.getWinner()
        );
    }

    @Test
    void shouldInitializeGameAsNotFinished() {

        Game game = new Game();

        assertFalse(
                game.isGameFinished()
        );
    }

    @Test
    void shouldInitializePlayerCountAsZero() {

        Game game = new Game();

        assertEquals(
                0,
                game.getPlayerCount()
        );
    }

    @Test
    void shouldSetAndGetRoomId() {

        Game game = new Game();

        game.setRoomId("ABC123");

        assertEquals(
                "ABC123",
                game.getRoomId()
        );
    }

    @Test
    void shouldSetAndGetWinner() {

        Game game = new Game();

        game.setWinner("X");

        assertEquals(
                "X",
                game.getWinner()
        );
    }

    @Test
    void shouldSetAndGetPlayerCount() {

        Game game = new Game();

        game.setPlayerCount(2);

        assertEquals(
                2,
                game.getPlayerCount()
        );
    }

    @Test
    void shouldSetGameFinished() {

        Game game = new Game();

        game.setGameFinished(true);

        assertTrue(
                game.isGameFinished()
        );
    }

    @Test
    void shouldAllowBoardModification() {

        Game game = new Game();

        game.getBoard()[1][1] = "X";

        assertEquals(
                "X",
                game.getBoard()[1][1]
        );
    }
}
