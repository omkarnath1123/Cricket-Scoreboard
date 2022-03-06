package com.omkar_nath.cricket_score_board.domain;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Inning firstInning;
    private Inning secondInning;
    private Inning currentInning;
    private int noOfOvers;
    private int teamSize;
    private List<String> firstTeam = new ArrayList<>();
    private List<String> secondTeam = new ArrayList<>();
    private String firstTeamName = null;
    private String secondTeamName = null;

    public Match(Integer teamSize) {
        this.firstInning = new Inning();
        this.secondInning = new Inning();
        this.currentInning = firstInning;
        this.noOfOvers = 0;
        this.teamSize = teamSize;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    public List<String> getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(List<String> firstTeam) {
        this.firstTeam = firstTeam;
    }

    public List<String> getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(List<String> secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Inning getFirstInning() {
        return firstInning;
    }

    public void setFirstInning(Inning firstInning) {
        this.firstInning = firstInning;
    }

    public Inning getSecondInning() {
        return secondInning;
    }

    public void setSecondInning(Inning secondInning) {
        this.secondInning = secondInning;
    }

    public Inning getCurrentInning() {
        return currentInning;
    }

    public void setCurrentInning(Inning currentInning) {
        this.currentInning = currentInning;
    }

    public int getNoOfOvers() {
        return noOfOvers;
    }

    public void setNoOfOvers(int noOfOvers) {
        this.noOfOvers = noOfOvers;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
