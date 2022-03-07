package com.omkar_nath.cricket_score_board.client;

import com.omkar_nath.cricket_score_board.excptions.CommandNotFoundException;
import com.omkar_nath.cricket_score_board.excptions.InvalidGameState;
import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.interaction.CommandFactory;
import com.omkar_nath.cricket_score_board.utils.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Client {

    private final BufferedReader inputReader;
    private final CommandFactory commandFactory;

    public Client(BufferedReader inputReader, CommandFactory commandFactory) {
        this.inputReader = inputReader;
        this.commandFactory = commandFactory;
    }

    public void handleInput() throws IOException {
        try {
            while (true) {
                String inputLine = this.inputReader.readLine();
                if (inputLine == null) {
                    break;
                }

                inputLine = inputLine.trim();
                if (inputLine.isEmpty()) {
                    continue;
                }

                if (inputLine.equalsIgnoreCase(Constant.EXIT)) {
                    break;
                }
                if (inputLine.equalsIgnoreCase("help")) {
                    commandFactory.listCommandHelp();
                    System.out.println(" ");
                    continue;
                }

                processInputLine(inputLine);
            }
        } finally {
            inputReader.close();
        }
    }

    private void processInputLine(String inputLine) {
        String[] inputChunks = inputLine.split(Constant.BLANK);

        String command = inputChunks[0];
        String[] params = Arrays.copyOfRange(inputChunks, 1, inputChunks.length);

        try {
            commandFactory.executeCommand(command, params);
        } catch (CommandNotFoundException | InvalidParameterException | InvalidGameState ex) {
            System.out.println(Constant.ERROR + ex.getMessage());
        }
    }
}
