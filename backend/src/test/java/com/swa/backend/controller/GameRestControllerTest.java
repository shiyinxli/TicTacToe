package com.swa.backend.controller;

import com.swa.backend.model.Game;
import com.swa.backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameService gameService;

    private String validRoomId;

    @BeforeEach
    void setup() {

        Game game = gameService.createGame();

        validRoomId = game.getRoomId();
    }

    @Test
    void shouldCreateGameEndpoint() throws Exception {

        mockMvc.perform(
                        post("/api/game/create")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.roomId").exists())

                .andExpect(jsonPath("$.currentPlayer")
                        .value("X"))

                .andExpect(jsonPath("$.winner")
                        .value(""))

                .andExpect(jsonPath("$.gameFinished")
                        .value(false))

                .andExpect(jsonPath("$.playerCount")
                        .value(1));
    }

    @Test
    void shouldReturnCorrectGameJson() throws Exception {

        mockMvc.perform(
                        post("/api/game/create")
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.board").isArray())

                .andExpect(jsonPath("$.board", hasSize(3)))

                .andExpect(jsonPath("$.board[0]", hasSize(3)))

                .andExpect(jsonPath("$.board[0][0]")
                        .value(""))

                .andExpect(jsonPath("$.board[1][1]")
                        .value(""))

                .andExpect(jsonPath("$.board[2][2]")
                        .value(""));
    }

    @Test
    void shouldJoinExistingRoom() throws Exception {

        mockMvc.perform(
                        post("/api/game/join/" + validRoomId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.roomId")
                        .value(validRoomId))

                .andExpect(jsonPath("$.playerCount")
                        .value(2));
    }

    @Test
    void shouldReturn404ForInvalidRoom() throws Exception {

        mockMvc.perform(
                        post("/api/game/join/INVALID")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRejectThirdPlayer() throws Exception {

        // second player joins successfully
        mockMvc.perform(
                post("/api/game/join/" + validRoomId)
        ).andExpect(status().isOk());

        // third player should fail
        mockMvc.perform(
                        post("/api/game/join/" + validRoomId)
                )
                .andExpect(status().isNotFound());
    }
}
