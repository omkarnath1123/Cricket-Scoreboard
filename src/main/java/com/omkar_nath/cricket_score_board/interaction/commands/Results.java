package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.Objects;

public class Results implements Command{
    private final CricketBoardHandler match;

    public Results(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.RESULTS.name();
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.nonNull(params) && params.length > 0)
            throw new InvalidParameterException("no parameter expected for results");

        this.match.declareResults();
    }
}
