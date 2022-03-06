package com.omkar_nath.cricket_score_board.domain;

public class BattingTeamPlayerProfile {

    private int runsScored;
    private int foursCount;
    private int sixCount;
    private int ballsPlayed;

    public BattingTeamPlayerProfile() {
        this.runsScored = this.foursCount = this.sixCount = this.ballsPlayed = 0;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getFoursCount() {
        return foursCount;
    }

    public int getSixCount() {
        return sixCount;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void addRunsScored(int runsScored) {
        this.runsScored = this.runsScored + runsScored;
    }

    public void addFoursCount() {
        this.foursCount++;
    }

    public void addSixCount() {
        this.sixCount++;
    }

    public void addBallsPlayed() {
        this.ballsPlayed++;
    }
}
