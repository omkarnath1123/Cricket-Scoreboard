package com.omkar_nath.cricket_score_board.client;

import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.interaction.CommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientFactoryTests {

    private static CommandFactory commandFactory;

    @BeforeAll
    public static void commandFactory() {
        CricketBoardHandler parkingLotCommandHandler = new CricketBoardHandler();
        commandFactory = CommandFactory.init(parkingLotCommandHandler);
    }

    @Test
    public void buildClientWithValidFilePath_shouldCreateFileClient() throws FileNotFoundException, IOException {
        String[] args = {"file_input.txt"};
        Client client = ClientFactory.buildClient(args, commandFactory);
        client.handleInput();
        assertTrue(client instanceof FileClient);
    }
}
