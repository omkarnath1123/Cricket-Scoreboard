package com.omkar_nath.cricket_score_board.interaction.commands;

import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;

public interface Command {
    String helpText();

    void execute(String[] params) throws InvalidParameterException;
}
