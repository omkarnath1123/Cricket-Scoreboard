package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.Objects;

public class BattingOrder implements Command {
    private final CricketBoardHandler match;

    public BattingOrder(CricketBoardHandler match) {
        this.match = match;
    }

    @Override
    public String helpText() {
        return CommandEnum.BATTING_ORDER.name() + "enter the names of players in order";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        int playersCount = this.match.getPlayersCount();
        if (Objects.isNull(params) || params.length != playersCount)
            throw new InvalidParameterException("Expected the names of " + playersCount + " players");

        this.match.updateBattingOrder(params);

    }
}
