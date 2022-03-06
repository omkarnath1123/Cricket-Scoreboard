package com.omkar_nath.cricket_score_board.interaction;

import com.omkar_nath.cricket_score_board.excptions.CommandNotFoundException;
import com.omkar_nath.cricket_score_board.excptions.InvalidParameterException;
import com.omkar_nath.cricket_score_board.handler.CricketBoardHandler;
import com.omkar_nath.cricket_score_board.interaction.commands.*;
import com.omkar_nath.cricket_score_board.utils.CommandEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {

    private final Map<CommandEnum, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public static CommandFactory init(CricketBoardHandler cricketBoardHandler) {
        final CommandFactory commands = new CommandFactory();
        commands.addCommand(CommandEnum.SCORE_BOARD, new ScoreBoard(cricketBoardHandler));
        commands.addCommand(CommandEnum.TEAM_PLAYERS, new BuildTeams(cricketBoardHandler));
        commands.addCommand(CommandEnum.BOLLING_ORDER, new BollingOrder(cricketBoardHandler));
        commands.addCommand(CommandEnum.BATTING_ORDER, new BattingOrder(cricketBoardHandler));
        commands.addCommand(CommandEnum.THIS_OVER, new ThisOver(cricketBoardHandler));
        commands.addCommand(CommandEnum.OVER_COUNT, new OverSize(cricketBoardHandler));
        commands.addCommand(CommandEnum.RESULTS, new Results(cricketBoardHandler));
        commands.addCommand(CommandEnum.TEAM_NAMES, new TeamName(cricketBoardHandler));
        return commands;
    }

    public void addCommand(CommandEnum name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name, String[] params) throws CommandNotFoundException, InvalidParameterException {
        CommandEnum method = CommandEnum.fromString(name);
        if (Objects.isNull(method)) {
            throw new CommandNotFoundException(name);
        }
        commands.get(method).execute(params);
    }

    public void listCommandHelp() {
        commands.keySet().stream()
                .map(command -> commands.get(command).helpText())
                .forEach(System.out::println);
    }
}
