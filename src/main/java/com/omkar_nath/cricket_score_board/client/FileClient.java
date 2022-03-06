package com.omkar_nath.cricket_score_board.client;

import com.omkar_nath.cricket_score_board.interaction.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client {
    public FileClient(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}
