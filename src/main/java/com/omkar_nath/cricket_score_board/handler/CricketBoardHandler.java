package com.omkar_nath.cricket_score_board.handler;

import com.omkar_nath.cricket_score_board.domain.BallState;
import com.omkar_nath.cricket_score_board.domain.BattingTeamProfile;
import com.omkar_nath.cricket_score_board.domain.CricketBoard;
import com.omkar_nath.cricket_score_board.domain.Over;
import com.omkar_nath.cricket_score_board.excptions.InvalidGameState;
import com.omkar_nath.cricket_score_board.utils.GameState;

import java.util.*;

public class CricketBoardHandler {
    private CricketBoard cricketBoard;

    public int getPlayersCount() {
        if (Objects.isNull(cricketBoard)) {
            throw new InvalidGameState("Match cricket board has not been created yet.");
        }
        return cricketBoard.getTeamSize();
    }

    public void buildTeams(int size) {
        cricketBoard = new CricketBoard(size);
        cricketBoard.setBallingTeam(new ArrayList<>(size));
        cricketBoard.setBattingTeam(new ArrayList<>(size));
    }

    public void showScoreBoard() {
        if (Objects.isNull(cricketBoard) || cricketBoard.getState() == GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Match has not been started yet.");
        }

        System.out.println("Player Name Score 4s 6s Balls");
        final String strikerEnd = cricketBoard.getStrikerEnd(),
                nonStrikerEnd = cricketBoard.getNonStrikerEnd();
        cricketBoard.getBattingTeamProfiles().forEach((key, profile) -> {
            boolean isOnField = key.equals(strikerEnd) ||
                    key.equals(nonStrikerEnd);
            System.out.println(key + (isOnField ? "*" : "") + " " +
                    profile.getRunsScored() + " " +
                    profile.getFoursCount() + " " +
                    profile.getSixCount() + " " +
                    profile.getBallsPlayed());
        });
        int wickets = cricketBoard.getWickets();
        System.out.println("Total: " + cricketBoard.getTotalScore() + "/" + wickets);
        System.out.println("Overs: " + cricketBoard.getCurrentOver());
    }

    public void updateBattingOrder(String[] players) {
        if (Objects.isNull(cricketBoard) || cricketBoard.getState() != GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Cannot change teams now game has already started.");
        }
        List<String> order = new LinkedList<>(Arrays.asList(players));
        cricketBoard.setBattingTeam(order);
        order.forEach(player -> cricketBoard.getBattingTeamProfiles().put(player, new BattingTeamProfile()));
    }

    public void updateBallingOrder(String[] players) {
        if (cricketBoard.getState() != GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Cannot change teams now game has already started.");
        }
        List<String> order = new LinkedList<>(Arrays.asList(players));
        cricketBoard.setBallingTeam(order);
    }

    public boolean isValidOver(String[] balls) {
        if (Objects.isNull(cricketBoard)) {
            throw new InvalidGameState("Match has not been started yet.");
        }
        int ballsCount = 0;
        for (String ball : balls) {
            BallState state = BallState.getState(ball);
            if (Objects.isNull(state))
                return false;
            if (state.getValidBall())
                ballsCount++;
        }
        return ballsCount == 6;
    }

    public void addOver(String[] balls) {
        if (Objects.isNull(cricketBoard)) {
            throw new InvalidGameState("Match has not been started yet.");
        }

        if (cricketBoard.getState() == GameState.ENDED)
            throw new InvalidGameState("Game already ended");

        if (cricketBoard.getState() == GameState.NOT_YET_STARTED) {
            cricketBoard.setState(GameState.ON_GOING);
            cricketBoard.setStrikerEnd(cricketBoard.getBattingTeam().get(0));
            cricketBoard.setNonStrikerEnd(cricketBoard.getBattingTeam().get(1));
        }

        cricketBoard.addCurrentOver();
        Map<String, BattingTeamProfile> team = cricketBoard.getBattingTeamProfiles();
        Over over = new Over();
        List<BallState> ballStates = new LinkedList<>();
        for (String ball : balls) {
            BallState state = BallState.getState(ball);
            ballStates.add(state);
            BattingTeamProfile striker = team.get(cricketBoard.getStrikerEnd());
            striker.addRunsScored(state.getRun());
            if (state.getValidBall())
                striker.addBallsPlayed();

            cricketBoard.appendTotalScore(state.getRun());
            if (state == BallState.WIDE)
                cricketBoard.appendTotalScore(1);

            if (state == BallState.FOUR)
                striker.addFoursCount();
            else if (state == BallState.SIX)
                striker.addSixCount();

            if (state.getRun() % 2 == 1) {
                swapStriker();
            }
            if (state == BallState.LEG_BEFORE_WICKET || state == BallState.WICKET) {
                cricketBoard.addWickets();
                int nextStrikerBatsmanPos = cricketBoard.getNextStrikerBatsmanPos();
                if (nextStrikerBatsmanPos == cricketBoard.getTeamSize() - 1) {
                    over.setNoBalls(Math.max(0, ballStates.size() - 6));
                    over.setBallStates(ballStates);
                    cricketBoard.getOvers().add(over);
                    cricketBoard.setState(GameState.ENDED);
                    return;
                }
                cricketBoard.setStrikerEnd(cricketBoard.getBattingTeam().get(nextStrikerBatsmanPos));
                cricketBoard.updateNextStrikerBatsmanPos();
            }
        }
        over.setNoBalls(ballStates.size() - 6);
        over.setBallStates(ballStates);
        cricketBoard.getOvers().add(over);
        swapStriker();

        if (cricketBoard.getOversCount() == cricketBoard.getCurrentOver())
            cricketBoard.setState(GameState.ENDED);
    }

    private void swapStriker() {
        String temp = cricketBoard.getStrikerEnd();
        cricketBoard.setStrikerEnd(cricketBoard.getNonStrikerEnd());
        cricketBoard.setNonStrikerEnd(temp);
    }
}
