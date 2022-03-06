package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;
import com.omkar_nath.cricket_score_board.utils.Config;
import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.StringUtils;

import java.util.Objects;

public class OverSize implements Command {
    private final CricketBoardHandler match;

    public OverSize(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.OVER_COUNT.name() + " " + Constant.OVER_COUNT_HELP_TEXT_MSG;
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.isNull(params) || params.length != 1)
            throw new InvalidParameterException("one parameter expected denoting the no of overs in the match");

        if (!StringUtils.isInteger(params[0]))
            throw new InvalidParameterException("no of overs must be an integer");

        Integer noOfOvers = Integer.parseInt(params[0]);
        if (noOfOvers < Config.MIN_OVER_MATCH || noOfOvers > Config.MAX_OVER_MATCH)
            throw new InvalidParameterException("match can only be help of min over : " + Config.MIN_OVER_MATCH + " and max over" + Config.MAX_OVER_MATCH);
        this.match.setOverInMatch(noOfOvers);
    }
}
