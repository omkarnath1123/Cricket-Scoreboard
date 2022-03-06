package com.omkar_nath.cricket_score_board.utils;

public enum CommandEnum {
    SCORE_BOARD("SHOW_SCORE_BOARD"),
    TEAM_PLAYERS("TEAM_PLAYERS"),
    BATTING_ORDER("BATTING_ORDER"),
    BOLLING_ORDER("BOLLING_ORDER"),
    THIS_OVER("THIS_OVER"),
    OVER_COUNT("OVER_COUNT"),
    RESULTS("RESULTS"),
    TEAM_NAMES("TEAM_NAMES");

    String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public static CommandEnum fromString(String text) {
        for (CommandEnum command : CommandEnum.values()) {
            if (command.value.equalsIgnoreCase(text)) {
                return command;
            }
        }
        return null;
    }
}
