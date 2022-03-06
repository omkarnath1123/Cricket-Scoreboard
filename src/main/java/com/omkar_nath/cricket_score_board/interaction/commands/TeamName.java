package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.Objects;

public class TeamName implements Command{

    private final CricketBoardHandler match;

    public TeamName(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.TEAM_NAMES.name() + "names of two teams";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.isNull(params) || params.length != 2)
            throw new InvalidParameterException("two names are required for team 1 and team 2");

        this.match.setNames(params[0], params[1]);
    }
}
