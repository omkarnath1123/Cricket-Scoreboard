package com.omkar_nath.cricket_score_board.domain;

public class BollingTeamPlayerProfile {

    private int medianOvers;
    private int dotBalls;
    private int wicketsTake;
    private int oversBowled;
    private int runsGiven;

    public BollingTeamPlayerProfile() {
        this.medianOvers = this.dotBalls = this.wicketsTake = this.oversBowled = this.runsGiven = 0;
    }

    public int getMedianOvers() {
        return medianOvers;
    }

    public void setMedianOvers(int medianOvers) {
        this.medianOvers = medianOvers;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void setDotBalls(int dotBalls) {
        this.dotBalls = dotBalls;
    }

    public int getWicketsTake() {
        return wicketsTake;
    }

    public void setWicketsTake(int wicketsTake) {
        this.wicketsTake = wicketsTake;
    }

    public int getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }
}
