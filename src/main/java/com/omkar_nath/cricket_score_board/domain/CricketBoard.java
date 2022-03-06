package com.omkar_nath.cricket_score_board.domain;

import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.GameState;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CricketBoard {
    private final int teamSize;
    private int wickets = 0;
    private final Map<String, BattingTeamProfile> battingTeamProfiles = new HashMap<>();
    private final Map<String, BattingTeamProfile> ballingTeamProfiles = new HashMap<>();
    private int oversCount = 0;
    private List<String> battingTeam;
    private List<String> ballingTeam;
    private List<Over> overs = new LinkedList<>();
    private int totalScore = 0;
    private GameState state = GameState.NOT_YET_STARTED;
    private int currentOver = 0;
    private String currentBaller = Constant.EMPTY_STRING;
    private String strikerEnd = Constant.EMPTY_STRING;
    private String nonStrikerEnd = Constant.EMPTY_STRING;
    private int nextStrikerBatsmanPos = 2;

    public CricketBoard(int teamSize) {
        this.teamSize = teamSize;
    }

    public Map<String, BattingTeamProfile> getBallingTeamProfiles() {
        return ballingTeamProfiles;
    }

    public int getWickets() {
        return wickets;
    }

    public void addWickets() {
        this.wickets++;
    }

    public void setOvers(List<Over> overs) {
        this.overs = overs;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Integer getCurrentOver() {
        return currentOver;
    }

    public void addCurrentOver() {
        this.currentOver++;
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

    public void appendTotalScore(int totalScore) {
        this.totalScore = this.totalScore + totalScore;
    }

    public Map<String, BattingTeamProfile> getBattingTeamProfiles() {
        return battingTeamProfiles;
    }

    public Map<String, BattingTeamProfile> getBollingTeamProfiles() {
        return ballingTeamProfiles;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public int getOversCount() {
        return oversCount;
    }

    public void setOversCount(int oversCount) {
        this.oversCount = oversCount;
    }

    public List<String> getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(List<String> battingTeam) {
        this.battingTeam = battingTeam;
    }

    public List<String> getBallingTeam() {
        return ballingTeam;
    }

    public void setBallingTeam(List<String> ballingTeam) {
        this.ballingTeam = ballingTeam;
    }
}
