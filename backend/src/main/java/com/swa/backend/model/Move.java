package com.swa.backend.model;

import lombok.Data;

@Data
public class Move {
    private String roomId;
    private int row;
    private int col;
    private String player;
}
