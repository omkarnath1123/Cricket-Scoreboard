package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;
import com.omkar_nath.cricket_score_board.utils.Config;
import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.StringUtils;

import java.util.Objects;

public class BuildTeams implements Command {
    private final CricketBoardHandler match;

    public BuildTeams(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.TEAM_PLAYERS.name() + Constant.BUILD_TEAMS_HELP_TEXT_MSG;
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (Objects.isNull(params) || params.length != 1) {
            throw new InvalidParameterException(Constant.BUILD_TEAMS_INVALID_PARAMS_LENGTH_MSG + CommandEnum.TEAM_PLAYERS.name() + Constant.REFER_HELP_TEXT_MSG);
        }
        if (!StringUtils.isInteger(params[0]))
            throw new InvalidParameterException("team size must be an integer");

        Integer teamSize = Integer.parseInt(params[0]);
        if (teamSize < Config.MIN_TEAM_SIZE || teamSize > Config.MAX_TEAM_SIZE)
            throw new InvalidParameterException("team size can only be help of min size : " + Config.MIN_TEAM_SIZE + " and max size" + Config.MAX_TEAM_SIZE);

        this.match.buildTeams(Integer.parseInt(params[0]));
    }
}
