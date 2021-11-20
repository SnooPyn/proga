package controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Invoker {
    // a map of commands, where the key is the string value (the name of the command), and the value is the command object itself
    private static Map<String, Commandable> commands = new TreeMap<>();
    // a sheet with the names of all commands entered by the user
    private static ArrayList<String> history = new ArrayList<>();

    public Commandable getCommand(String commandname) {
        return commands.get(commandname);
    }

    public static ArrayList<String> getHistory() {
        return history;
    }

    public static void setCommands(Map<String, Commandable> commands) {
        Invoker.commands = commands;
    }

    //a method that adds commands to the command map
    public void regist(Commandable... commands) {
        for (Commandable command : commands)
            Invoker.commands.put(command.getName(), command);

    }

    /**
     * this is a method that calls execute for a certain command
     * there is so much code due to command validation
     * in the method argument we pass a string containing the command and its argument ("update 1" or "show")
     */
    public String executeCommand(String commandName) throws IOException {
        try {
            commandName = commandName.replace(" *", "");
            String[] nameAndArgument = commandName.split(" ");
            // checking whether the command is equal to an empty value (if the user poked enter)
            if (!commandName.equals("")) {
                // checking if there is such a command at all
                if (commands.get(nameAndArgument[0]) == null) {
                    return ("There is no such command. Enter the \"help\".");
                } else {
                    // checking whether this command has an argument
                    if (commands.get(nameAndArgument[0]) instanceof CommandWithoutArg) {
                        // if this command should not have an argument,
                        try {
                            /**
                             * we will try to get an argument from the array name And Argument
                             * if it turned out and the error did not crash, then we will write that the command does not match the format
                             */
                            String s = nameAndArgument[1];
                            return ("Invalid command format, Enter the \"help\".");
                        } catch (Exception e) {
                            /**
                             * if the user did not specify an argument, then everything is ok
                             * we get the command object by the name of the command and the executive without an argument (that is, we pass null in the arguments of the method)
                             */
                            history.add(nameAndArgument[0]);
                            return commands.get(nameAndArgument[0]).execute(null);
                        }
                    } else {
                        // if this command should have an argument
                        try {
                            //we will try to get the second argument from the array name And Argument (like in case of ""update 3 collection.xml)
                            String s = nameAndArgument[2];
                            return ("Invalid command format, Enter the \"help\".");
                        } catch (IndexOutOfBoundsException e1) {
                            try {
                                // we get a command object by the name of the command and an executive with an argument nameAndArgument[1]
                                history.add(nameAndArgument[0] + " " + nameAndArgument[1]);
                                return commands.get(nameAndArgument[0]).execute(nameAndArgument[1].trim());
                            } catch (IndexOutOfBoundsException | FileNotFoundException e2) {
                                // if there was no argument at all
                                return ("Invalid command format, Enter the \"help\".");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid argument");
        }
        return "";
    }
}