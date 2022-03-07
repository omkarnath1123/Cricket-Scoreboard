package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.exceptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;
import com.omkar_nath.cricket_score_board.utils.Constant;

import java.util.Objects;

public class ScoreBoard implements Command {
    private final CricketBoardHandler match;

    public ScoreBoard(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.SCORE_BOARD.name();
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.nonNull(params) && params.length > 0)
            throw new InvalidParameterException(Constant.SCORE_BOARD_INVALID_PARAMS_LENGTH_MSG);

        this.match.showScoreBoard();
    }
}
