package com.omkar_nath.cricket_score_board;

import com.omkar_nath.cricket_score_board.client.Client;
import com.omkar_nath.cricket_score_board.client.ClientFactory;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.interaction.CommandFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CricketBoardApplication {

    public static void main(String[] args) {
        CricketBoardHandler match = new CricketBoardHandler();
        CommandFactory commandFactory = CommandFactory.init(match);

        try {
            Client client = ClientFactory.buildClient(args, commandFactory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }
}
