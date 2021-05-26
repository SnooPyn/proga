package collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.ScriptRecursionException;
import run.Application;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private MovieAsker movieAsker;
    private List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, MovieAsker movieAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.movieAsker = movieAsker;
    }
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(Application.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printerror("No user input detected!");
        } catch (IllegalStateException exception) {
            Console.printerror("Unexpected error!");
        }
    }
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = movieAsker.getUserScanner();
            movieAsker.setUserScanner(scriptScanner);
            movieAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(Application.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            movieAsker.setUserScanner(tmpScanner);
            movieAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check the script for the correctness of the entered data!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("The script file was not found!");
        } catch (NoSuchElementException exception) {
            Console.printerror("The script file is empty!");
        } catch (ScriptRecursionException exception) {
            Console.printerror("Scripts cannot be called recursively!");
        } catch (IllegalStateException exception) {
            Console.printerror("Unexpected error!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_min":
                if (!commandManager.addIfMin(userCommand[1])) return 1;
                break;
            case "add_if_max":
                if (!commandManager.addIfMax(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1])) return 1;
                break;
            case "max_by_oscars_count":
                if (!commandManager.maxByOscarsCount(userCommand[1])) return 1;
                break;
            case "filter_less_than_mpaa_rating":
                if (!commandManager.filterLessThanMpaaRating(userCommand[1])) return 1;
                break;
            case "print_field_descending_oscars_count":
                if (!commandManager.printFieldDescendingOscarsCount(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
    /**
     * Prints toOut.toString() to Console
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints toOut.toString() + \n to Console
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints error: toOut.toString() to Console
     * @param toOut Error to print
     */
    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }

    /**
     * Prints formatted 2-element table to Console
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }

    @Override
    public String toString() {
        return "Console (class for processing command input)";
    }
}
