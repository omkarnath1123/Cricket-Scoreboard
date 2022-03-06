package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.Objects;

public class BollingOrder implements Command{

    private final CricketBoardHandler match;

    public BollingOrder(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.BOLLING_ORDER.name() + "enter the names of players in order";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        int noOfOvers = this.match.getNumberOfOvers();
        if (Objects.isNull(params) || params.length != noOfOvers)
            throw new InvalidParameterException("Expected the names of " + noOfOvers + " players");

        this.match.updateBollingOrder(params);
    }
}
