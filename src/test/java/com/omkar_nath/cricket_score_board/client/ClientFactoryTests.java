package com.omkar_nath.cricket_score_board.client;

import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.interaction.CommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientFactoryTests {

    private static CommandFactory commandFactory;

    @BeforeAll
    public static void commandFactory() {
        CricketBoardHandler parkingLotCommandHandler = new CricketBoardHandler();
        commandFactory = CommandFactory.init(parkingLotCommandHandler);
    }

    @Test
    public void buildClientWithNoArg() throws FileNotFoundException {
        String[] args = {};
        Client client = ClientFactory.buildClient(args, commandFactory);
        assertTrue(client instanceof CliClient);
    }

    @Test
    public void buildClientWithValidFilePath() throws FileNotFoundException, IOException {
        String[] args = {"file_input.txt"};
        Client client = ClientFactory.buildClient(args, commandFactory);
        client.handleInput();
        assertTrue(client instanceof FileClient);
    }

    @Test
    public void buildClientWithInvalidFilePath() throws FileNotFoundException {
        String[] args = {"invalid_file_path.txt"};
        assertThrows(FileNotFoundException.class, () -> ClientFactory.buildClient(args, commandFactory));
    }
}
