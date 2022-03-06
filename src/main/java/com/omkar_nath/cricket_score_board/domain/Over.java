package com.omkar_nath.cricket_score_board.domain;

import java.util.LinkedList;
import java.util.List;

public class Over {
    private final Integer balls = 6;
    List<BallState> ballStates = new LinkedList<>();
    private Integer noBalls = 0;

    public Integer getBalls() {
        return balls;
    }

    public List<BallState> getBallStates() {
        return ballStates;
    }

    public void setBallStates(List<BallState> ballStates) {
        this.ballStates = ballStates;
    }

    public Integer getNoBalls() {
        return noBalls;
    }

    public void setNoBalls(Integer noBalls) {
        this.noBalls = noBalls;
    }
}
