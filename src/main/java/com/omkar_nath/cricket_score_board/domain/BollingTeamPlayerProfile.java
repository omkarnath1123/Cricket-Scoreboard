package com.omkar_nath.cricket_score_board.domain;

public class BollingTeamPlayerProfile {

    private int madienOvers;
    private int dotBalls;
    private int wicketsTaken;
    private double oversBowled;
    private int runsGiven;

    public BollingTeamPlayerProfile() {
        this.madienOvers = this.dotBalls = this.wicketsTaken = this.runsGiven = 0;
        this.oversBowled = 0D;
    }

    public int getMadienOvers() {
        return madienOvers;
    }

    public void addMedianOvers() {
        this.madienOvers++;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void addDotBalls(int dotBalls) {
        this.dotBalls = this.dotBalls + dotBalls;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void addWicketsTaken() {
        this.wicketsTaken++;
    }

    public double getOversBowled() {
        return oversBowled;
    }

    public void addOversBowled(double oversBowled) {
        this.oversBowled = oversBowled + this.oversBowled;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void addRunsGiven(int runsGiven) {
        this.runsGiven = this.runsGiven + runsGiven;
    }
}
