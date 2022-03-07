package com.omkar_nath.cricket_score_board.handler;

import com.omkar_nath.cricket_score_board.domain.*;
import com.omkar_nath.cricket_score_board.exceptions.InvalidGameState;
import com.omkar_nath.cricket_score_board.utils.Config;
import com.omkar_nath.cricket_score_board.utils.Constant;
import com.omkar_nath.cricket_score_board.utils.GameState;
import com.omkar_nath.cricket_score_board.utils.StringUtils;

import java.util.*;

public class CricketBoardHandler {
    private Match match;

    public int getPlayersCount() {
        Inning currentInning = match.getCurrentInning();
        if (Objects.isNull(currentInning)) {
            throw new InvalidGameState("Match cricket board has not been created yet.");
        }
        return match.getTeamSize();
    }

    public int getNumberOfOvers() {
        Inning currentInning = match.getCurrentInning();
        if (Objects.isNull(currentInning)) {
            throw new InvalidGameState("Match cricket board has not been created yet.");
        }
        return match.getNoOfOvers();
    }

    public void buildTeams(int size) {
        match = new Match(size);
    }

    public void showScoreBoard() {
        Inning currentInning = match.getCurrentInning();
        if (Objects.isNull(currentInning) || currentInning.getState() == GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Match has not been started yet.");
        }

        boolean isFirstInning = match.getCurrentInning() == match.getFirstInning();
        String battingTeam = isFirstInning ? match.getFirstTeamName() : match.getSecondTeamName();
        String bollingTeam = !isFirstInning ? match.getFirstTeamName() : match.getSecondTeamName();

        System.out.println(Constant.EMPTY_STRING);
        System.out.println("Score card for : " + battingTeam);
        System.out.println(
                StringUtils.rightPadSpaces("Player Name", Config.MAX_NAME_LEN) + " " +
                        StringUtils.rightPadSpaces("Score", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("4s", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("6s", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Balls", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Strike Rate", Config.MAX_DETAILS_LEN));
        final String strikerEnd = currentInning.getStrikerEnd(),
                nonStrikerEnd = currentInning.getNonStrikerEnd();
        currentInning.getBattingTeamProfiles().forEach((name, profile) -> {
            boolean isOnField = name.equals(strikerEnd) ||
                    name.equals(nonStrikerEnd);
            double strikeRate = (double) profile.getRunsScored() / profile.getBallsPlayed();

            System.out.println(
                    StringUtils.rightPadSpaces(name + (isOnField ? "*" : ""), Config.MAX_NAME_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getRunsScored(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getFoursCount(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getSixCount(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getBallsPlayed(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(strikeRate, Config.MAX_DETAILS_LEN));
        });
        int wickets = currentInning.getWickets();
        System.out.println("Total: " + currentInning.getTotalScore() + "/" + wickets);
        System.out.println("Overs: " + currentInning.getCurrentOver());

        System.out.println(Constant.EMPTY_STRING);
        System.out.println("Score card for : " + bollingTeam);
        System.out.println(
                StringUtils.rightPadSpaces("Player Name", Config.MAX_NAME_LEN) + " " +
                        StringUtils.rightPadSpaces("Overs Balled", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Madien Overs", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Dot Balls", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Wickets Taken", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Runs Given", Config.MAX_DETAILS_LEN) + " " +
                        StringUtils.rightPadSpaces("Economy", Config.MAX_DETAILS_LEN)
        );
        currentInning.getBollingTeamProfiles().forEach((name, profile) -> {
            double economy = profile.getRunsGiven() / profile.getOversBowled();
            System.out.println(
                    StringUtils.rightPadSpaces(name, Config.MAX_NAME_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getOversBowled(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getMadienOvers(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getDotBalls(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getWicketsTaken(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(profile.getRunsGiven(), Config.MAX_DETAILS_LEN) + " " +
                            StringUtils.rightPadSpaces(economy, Config.MAX_DETAILS_LEN)
            );
        });
    }

    public void updateBattingOrder(String[] players) {
        Inning currentInning = match.getCurrentInning();
        if (currentInning.getState() == GameState.ENDED && currentInning == match.getFirstInning()) {
            match.setCurrentInning(match.getSecondInning());
            currentInning = match.getCurrentInning();
        }
        if (Objects.isNull(currentInning) || currentInning.getState() != GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Cannot change teams now game has already started.");
        }
        List<String> order = new LinkedList<>(Arrays.asList(players));
        match.setFirstTeam(order);
        Inning finalCurrentInning = currentInning;
        order.forEach(player -> finalCurrentInning.getBattingTeamProfiles().put(player, new BattingTeamPlayerProfile()));
    }

    public void updateBollingOrder(String[] players) {
        Inning currentInning = match.getCurrentInning();
        if (currentInning.getState() == GameState.ENDED && currentInning == match.getFirstInning()) {
            match.setCurrentInning(match.getSecondInning());
            currentInning = match.getCurrentInning();
        }
        if (currentInning.getState() != GameState.NOT_YET_STARTED) {
            throw new InvalidGameState("Cannot change teams now game has already started.");
        }
        List<String> order = new LinkedList<>(Arrays.asList(players));
        match.setSecondTeam(order);
        Inning finalCurrentInning = currentInning;
        order.forEach(player -> finalCurrentInning.getBollingTeamProfiles().put(player, new BollingTeamPlayerProfile()));
    }

    public boolean isValidOver(String[] balls) {
        Inning currentInning = match.getCurrentInning();
        if (Objects.isNull(currentInning)) {
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
        return ballsCount <= Config.DEFAULT_OVER_SIZE;
    }

    public void addOver(String[] balls) {
        Inning currentInning = match.getCurrentInning();
        if (Objects.isNull(currentInning)) {
            throw new InvalidGameState("Match has not been started yet.");
        }

        if (currentInning.getState() == GameState.ENDED) {
            throw new InvalidGameState("Game already ended");
        }

        if (currentInning.getState() == GameState.NOT_YET_STARTED) {
            currentInning.setState(GameState.ON_GOING);
            currentInning.setStrikerEnd(match.getFirstTeam().get(0));
            currentInning.setNonStrikerEnd(match.getFirstTeam().get(1));
        }

        int noBalls = 0;
        int dotBalls = 0;
        int currOverRun = 0;
        int currentOver = (int) currentInning.getCurrentOver();
        Map<String, BattingTeamPlayerProfile> team = currentInning.getBattingTeamProfiles();
        BollingTeamPlayerProfile bowler = currentInning.getBollingTeamProfiles().get(match.getSecondTeam().get(currentOver));
        Over over = new Over();

        List<BallState> ballStates = new LinkedList<>();
        for (String ball : balls) {
            BallState state = BallState.getState(ball);
            ballStates.add(state);
            BattingTeamPlayerProfile striker = team.get(currentInning.getStrikerEnd());
            striker.addRunsScored(state.getRun());
            currOverRun = currOverRun + state.getRun();
            bowler.addRunsGiven(state.getRun());
            if (state.getValidBall())
                striker.addBallsPlayed();

            currentInning.appendTotalScore(state.getRun());
            if (state == BallState.WIDE) {
                currentInning.appendTotalScore(1);
                noBalls++;
            } else if (state == BallState.NO_BALL)
                noBalls = noBalls + 2;
            else if (state == BallState.FOUR)
                striker.addFoursCount();
            else if (state == BallState.SIX)
                striker.addSixCount();
            else if (state == BallState.WICKET)
                bowler.addWicketsTaken();
            else if (state == BallState.DOT_BALLS)
                dotBalls++;

            if (state.getRun() % 2 == 1) {
                swapStriker();
            }
            if (state == BallState.LEG_BEFORE_WICKET || state == BallState.WICKET) {
                currentInning.addWickets();
                int nextStrikerBatsmanPos = currentInning.getNextStrikerBatsmanPos();
                if (nextStrikerBatsmanPos == match.getTeamSize()) {
                    over.setNoBalls(Math.max(0, ballStates.size() - 6));
                    over.setBallStates(ballStates);
                    currentInning.getOvers().add(over);
                    currentInning.setState(GameState.ENDED);
                    int normalBalls = ballStates.size() - noBalls;
                    boolean isCompleteOver = normalBalls == 6;
                    double overSize = isCompleteOver ? 1 : 0.1 * normalBalls;
                    currentInning.addCurrentOver(overSize);
                    bowler.addOversBowled(overSize);
                    bowler.addDotBalls(dotBalls);
                    if (currOverRun == 0)
                        bowler.addMedianOvers();
                    return;
                }
                currentInning.setStrikerEnd(match.getFirstTeam().get(nextStrikerBatsmanPos));
                currentInning.updateNextStrikerBatsmanPos();
            }
        }
        bowler.addOversBowled(1D);
        bowler.addDotBalls(dotBalls);
        if (currOverRun == 0)
            bowler.addMedianOvers();
        currentInning.addCurrentOver(1D);
        over.setNoBalls(noBalls);
        over.setBallStates(ballStates);
        currentInning.getOvers().add(over);
        swapStriker();

        if (match.getNoOfOvers() == currentInning.getCurrentOver()) {
            currentInning.setState(GameState.ENDED);
        }

    }

    private void swapStriker() {
        Inning currentInning = match.getCurrentInning();
        String temp = currentInning.getStrikerEnd();
        currentInning.setStrikerEnd(currentInning.getNonStrikerEnd());
        currentInning.setNonStrikerEnd(temp);
    }

    public void setOverInMatch(int noOfOvers) {
        if (Objects.isNull(match))
            throw new InvalidGameState("Match has not been created yet");

        match.setNoOfOvers(noOfOvers);
    }

    public void declareResults() {
        Inning currentInning = match.getCurrentInning();
        if (currentInning.getState() != GameState.ENDED)
            throw new InvalidGameState("Match has not been ended to declare the winner");

        Inning first = match.getFirstInning();
        Inning second = match.getSecondInning();
        String firstTeamName = match.getFirstTeamName() == null ? "First" : match.getFirstTeamName();
        String secondTeamName = match.getSecondTeamName() == null ? "Second" : match.getSecondTeamName();
        int runsDiff = Math.abs(first.getTotalScore() - second.getTotalScore());
        System.out.println(Constant.EMPTY_STRING);
        System.out.print("Result : ");
        if (first.getTotalScore() > second.getTotalScore())
            System.out.println(firstTeamName + " team won the match by " + runsDiff + " runs");
        else if (first.getTotalScore() < second.getTotalScore())
            System.out.println(secondTeamName + " team won the match by " + runsDiff + " runs");
        else
            System.out.println("Match is tie");
    }

    public void setNames(String firstName, String secondName) {
        if (Objects.isNull(match))
            throw new InvalidGameState("Match has not been created yet");

        this.match.setFirstTeamName(firstName);
        this.match.setSecondTeamName(secondName);
    }
}
