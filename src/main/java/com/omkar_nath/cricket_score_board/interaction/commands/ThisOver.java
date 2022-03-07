package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.Objects;

public class ThisOver implements Command {
    private final CricketBoardHandler match;

    public ThisOver(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.THIS_OVER.name() + " the events in the six balls";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.isNull(params) || !this.match.isValidOver(params))
            throw new InvalidParameterException("Expected six balls with the given events");

        this.match.addOver(params);
    }
}
