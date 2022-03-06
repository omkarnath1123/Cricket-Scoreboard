package com.omkar_nath.cricket_score_board.domain;

import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.GameState;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Inning {
    private final Map<String, BattingTeamPlayerProfile> battingTeamProfiles = new LinkedHashMap<>();
    private final Map<String, BollingTeamPlayerProfile> bollingTeamProfiles = new LinkedHashMap<>();
    private int wickets = 0;
    private List<Over> overs = new LinkedList<>();
    private int totalScore = 0;
    private GameState state = GameState.NOT_YET_STARTED;
    private double currentOver = 0D;
    private String currentBaller = Constant.EMPTY_STRING;
    private String strikerEnd = Constant.EMPTY_STRING;
    private String nonStrikerEnd = Constant.EMPTY_STRING;
    private int nextStrikerBatsmanPos = 2;

    public Map<String, BollingTeamPlayerProfile> getBollingTeamProfiles() {
        return bollingTeamProfiles;
    }

    public int getWickets() {
        return wickets;
    }

    public void addWickets() {
        this.wickets++;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public double getCurrentOver() {
        return currentOver;
    }

    public void addCurrentOver(double over) {
        this.currentOver = this.currentOver + over;
    }

    public String getCurrentBaller() {
        return currentBaller;
    }

    public void setCurrentBaller(String currentBaller) {
        this.currentBaller = currentBaller;
    }

    public String getStrikerEnd() {
        return strikerEnd;
    }

    public void setStrikerEnd(String strikerEnd) {
        this.strikerEnd = strikerEnd;
    }

    public String getNonStrikerEnd() {
        return nonStrikerEnd;
    }

    public void setNonStrikerEnd(String nonStrikerEnd) {
        this.nonStrikerEnd = nonStrikerEnd;
    }

    public Integer getNextStrikerBatsmanPos() {
        return nextStrikerBatsmanPos;
    }

    public void updateNextStrikerBatsmanPos() {
        this.nextStrikerBatsmanPos++;
    }

    public List<Over> getOvers() {
        return overs;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void appendTotalScore(int totalScore) {
        this.totalScore = this.totalScore + totalScore;
    }

    public Map<String, BattingTeamPlayerProfile> getBattingTeamProfiles() {
        return battingTeamProfiles;
    }
}
