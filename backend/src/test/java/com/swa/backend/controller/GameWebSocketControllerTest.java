package com.swa.backend.controller;

import com.swa.backend.model.Game;
import com.swa.backend.model.Move;
import com.swa.backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameWebSocketControllerTest {

    private GameService gameService;

    private SimpMessagingTemplate messagingTemplate;

    private GameWebSocketController controller;

    private String roomId;

    @BeforeEach
    void setup() {

        gameService = new GameService();

        messagingTemplate = mock(SimpMessagingTemplate.class);

        controller = new GameWebSocketController(
                gameService,
                messagingTemplate
        );

        Game game = gameService.createGame();

        roomId = game.getRoomId();
    }

    @Test
    void shouldHandleMoveMessage() {

        Move move = new Move();

        move.setRoomId(roomId);
        move.setRow(0);
        move.setCol(0);
        move.setPlayer("X");

        controller.handleMove(move);

        Game updatedGame = gameService.getGame(roomId);

        assertEquals(
                "X",
                updatedGame.getBoard()[0][0]
        );

        assertEquals(
                "O",
                updatedGame.getCurrentPlayer()
        );
    }

    @Test
    void shouldBroadcastUpdatedGame() {

        Move move = new Move();

        move.setRoomId(roomId);
        move.setRow(1);
        move.setCol(1);
        move.setPlayer("X");

        controller.handleMove(move);

        verify(messagingTemplate, times(1))
                .convertAndSend(
                        eq("/topic/game/" + roomId),
                        any(Game.class)
                );
    }

    @Test
    void shouldBroadcastCorrectGameState() {

        Move move = new Move();

        move.setRoomId(roomId);
        move.setRow(2);
        move.setCol(2);
        move.setPlayer("X");

        controller.handleMove(move);

        ArgumentCaptor<Game> gameCaptor =
                ArgumentCaptor.forClass(Game.class);

        verify(messagingTemplate)
                .convertAndSend(
                        eq("/topic/game/" + roomId),
                        gameCaptor.capture()
                );

        Game broadcastedGame = gameCaptor.getValue();

        assertEquals(
                "X",
                broadcastedGame.getBoard()[2][2]
        );

        assertEquals(
                "O",
                broadcastedGame.getCurrentPlayer()
        );
    }

    @Test
    void shouldRejectInvalidMove() {

        Move firstMove = new Move();

        firstMove.setRoomId(roomId);
        firstMove.setRow(0);
        firstMove.setCol(0);
        firstMove.setPlayer("X");

        controller.handleMove(firstMove);

        // try to overwrite same cell
        Move invalidMove = new Move();

        invalidMove.setRoomId(roomId);
        invalidMove.setRow(0);
        invalidMove.setCol(0);
        invalidMove.setPlayer("O");

        controller.handleMove(invalidMove);

        Game updatedGame = gameService.getGame(roomId);

        // cell should still contain X
        assertEquals(
                "X",
                updatedGame.getBoard()[0][0]
        );
    }

    @Test
    void shouldPreventWrongTurnMove() {

        Move wrongTurnMove = new Move();

        wrongTurnMove.setRoomId(roomId);
        wrongTurnMove.setRow(1);
        wrongTurnMove.setCol(1);
        wrongTurnMove.setPlayer("O");

        controller.handleMove(wrongTurnMove);

        Game updatedGame = gameService.getGame(roomId);

        // move should be rejected because X starts first
        assertEquals(
                "",
                updatedGame.getBoard()[1][1]
        );

        assertEquals(
                "X",
                updatedGame.getCurrentPlayer()
        );
    }

    @Test
    void shouldDetectWinnerAndBroadcastFinishedGame() {

        controller.handleMove(createMove(0, 0, "X"));
        controller.handleMove(createMove(1, 0, "O"));

        controller.handleMove(createMove(0, 1, "X"));
        controller.handleMove(createMove(1, 1, "O"));

        controller.handleMove(createMove(0, 2, "X"));

        Game updatedGame = gameService.getGame(roomId);

        assertTrue(updatedGame.isGameFinished());

        assertEquals(
                "X",
                updatedGame.getWinner()
        );
    }

    @Test
    void shouldPreventMovesAfterGameFinished() {

        controller.handleMove(createMove(0, 0, "X"));
        controller.handleMove(createMove(1, 0, "O"));

        controller.handleMove(createMove(0, 1, "X"));
        controller.handleMove(createMove(1, 1, "O"));

        controller.handleMove(createMove(0, 2, "X"));

        // attempt extra move after game ended
        controller.handleMove(createMove(2, 2, "O"));

        Game updatedGame = gameService.getGame(roomId);

        assertEquals(
                "",
                updatedGame.getBoard()[2][2]
        );
    }

    private Move createMove(int row, int col, String player) {

        Move move = new Move();

        move.setRoomId(roomId);
        move.setRow(row);
        move.setCol(col);
        move.setPlayer(player);

        return move;
    }
}
