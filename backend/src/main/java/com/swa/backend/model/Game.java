package com.swa.backend.model;

import lombok.Data;

@Data
public class Game {
    private String roomId;

    private String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };
    private String currentPlayer = "X";
    private String winner = "";
    private boolean gameFinished = false;
    private int playerCount = 0;
}
