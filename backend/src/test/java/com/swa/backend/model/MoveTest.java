package com.swa.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void shouldSetAndGetRoomId() {

        Move move = new Move();

        move.setRoomId("ROOM1");

        assertEquals(
                "ROOM1",
                move.getRoomId()
        );
    }

    @Test
    void shouldSetAndGetRow() {

        Move move = new Move();

        move.setRow(1);

        assertEquals(
                1,
                move.getRow()
        );
    }

    @Test
    void shouldSetAndGetColumn() {

        Move move = new Move();

        move.setCol(2);

        assertEquals(
                2,
                move.getCol()
        );
    }

    @Test
    void shouldSetAndGetPlayer() {

        Move move = new Move();

        move.setPlayer("X");

        assertEquals(
                "X",
                move.getPlayer()
        );
    }

    @Test
    void shouldStoreCompleteMoveData() {

        Move move = new Move();

        move.setRoomId("ABC123");
        move.setRow(0);
        move.setCol(1);
        move.setPlayer("O");

        assertEquals(
                "ABC123",
                move.getRoomId()
        );

        assertEquals(
                0,
                move.getRow()
        );

        assertEquals(
                1,
                move.getCol()
        );

        assertEquals(
                "O",
                move.getPlayer()
        );
    }

    @Test
    void shouldAllowNegativeValuesIfNotValidated() {

        Move move = new Move();

        move.setRow(-1);
        move.setCol(-5);

        assertEquals(
                -1,
                move.getRow()
        );

        assertEquals(
                -5,
                move.getCol()
        );
    }

    @Test
    void shouldAllowNullPlayerIfNotValidated() {

        Move move = new Move();

        move.setPlayer(null);

        assertNull(
                move.getPlayer()
        );
    }

    @Test
    void shouldAllowNullRoomIdIfNotValidated() {

        Move move = new Move();

        move.setRoomId(null);

        assertNull(
                move.getRoomId()
        );
    }
}