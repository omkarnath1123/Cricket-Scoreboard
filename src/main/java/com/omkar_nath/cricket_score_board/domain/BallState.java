package com.omkar_nath.cricket_score_board.domain;

import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.GameState;
import com.omkar_nath.cricket_score_board.utils.StringUtils;

import java.util.Objects;

public enum BallState {
    ONE_RUN(1, true),
    TWO_RUN(2, true),
    THREE_RUN(3, true),
    FOUR(4, true),
    SIX(6, true),
    WICKET("W", true),
    LEG_BEFORE_WICKET("LBW", true),
    WIDE("Wd", false),
    NO_BALL("No. Ball", false);

    private final Integer run;
    private final String event;
    private final boolean isValidBall;

    public Integer getRun() {
        return run;
    }

    public boolean getValidBall() {
        return isValidBall;
    }

    public String getEvent() {
        return event;
    }

    BallState(Integer run, boolean isValidBall) {
        this.run = run;
        this.isValidBall = isValidBall;
        this.event = Constant.EMPTY_STRING;
    }

    BallState(String event, boolean isValidBall) {
        this.run = 0;
        this.isValidBall = isValidBall;
        this.event = event;
    }

    public static BallState getState(String event) {
        Integer run = null;
        if (StringUtils.isInteger(event))
            run = Integer.parseInt(event);

        for (BallState state : BallState.values()) {
            if (Objects.nonNull(run) && Objects.equals(state.run, run))
                return state;
            else if (state.event.equals(event))
                return state;
        }
        return null;
    }
}
