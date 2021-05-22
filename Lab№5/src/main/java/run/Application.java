package run;

import java.util.Scanner;

import collections.Console;
import commands.*;
import collections.*;

/**
 * Main application class. Creates all instances and runs the program.
 */

public class Application {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {

            MovieAsker movieAsker = new MovieAsker(userScanner);
            FileManager fileManager = new FileManager(args[0]);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(

                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager,movieAsker),
                    new UpdateCommand(collectionManager, movieAsker),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ExitCommand(),
                    new ExecuteScriptCommand(),
                    new AddIfMinCommand(collectionManager, movieAsker),
                    new AddIfMaxCommand(collectionManager, movieAsker),
                    new HistoryCommand(),
                    new MaxByOscarsCountCommand(collectionManager),
                    new FilterLessThanMpaaRatingCommand(collectionManager),
                    new PrintFieldDescendingOscarsCountCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner, movieAsker);

            console.interactiveMode();
        }
    }
}