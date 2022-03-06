package com.omkar_nath.cricket_score_board.client;

import com.omkar_nath.cricket_score_board.interaction.CommandFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ClientFactory {

    public static Client buildClient(String[] args, CommandFactory commandFactory) throws FileNotFoundException {
        if (args.length == 0) {
            return new CliClient(new BufferedReader(new InputStreamReader(System.in)), commandFactory);
        } else {
            return new FileClient(new BufferedReader(new FileReader(args[0])), commandFactory);
        }
    }
}
