package commands;


import connection.App;
import connection.ClientReceiver;
import connection.ClientSender;
import controller.Collection;
import controller.CommandWithObject;
import controller.Commandable;
import controller.Commands;
import utilites.CreateTicket;
import utilites.ReaderFromFile;
import shells.RequestShell;
import shells.ResponseShell;
import shells.TicketShell;
import state.ResponseStatus;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScript implements Commandable {
    String name = "execute_script";
    private Set<String> scripts = new HashSet() {
    };

    /**
     * executes script with the name written in command line
     * executes script's name and script's names writen in this script are saved int static variable to prevent collisions
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        if (scripts.contains((String) arg))
            return "Impossible to execute to avoid recursion";
        scripts.add((String) arg);
        ClientSender sender = App.getSender();
        ClientReceiver receiver = App.getReceiver();
        String login = App.getLogin();
        String password = App.getPassword();
        try {
            String data = ReaderFromFile.read((String) arg);
            Commands command = new Commands();
            if (data != null) {
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    String commandName = commands[i];
                    System.out.println("Command \"" + commandName + "\":");
                    System.out.print("\t");
                    command.setCommand(commandName);
                    if (command.getMessage() != (null))
                        System.out.println(command.getMessage());
                    if (command.getCommand() != null) {
                        if (command.getCommand().getName().equals("execute_script")) {
                            System.out.println(command.getCommand().execute(command.getArg()));
                        }
                        else if (command.getCommand().getName().equals("help")){
                            System.out.println(command.getCommand().execute(command.getArg()));
                        }
                        else if (command.getCommand().getName().equals("exit")) {
                            System.out.println(command.getCommand().execute(command.getArg()));
                            sender.send(new RequestShell(sender.getClientPort(), login, password, command.getCommand(), command.getArg()));
                        } else {
                            sender.send(new RequestShell(sender.getClientPort(), login, password, command.getCommand(), command.getArg()));
                            if (command.getCommand() instanceof CommandWithObject) {
                                ResponseShell responseCheck = (ResponseShell) receiver.receiveObject();
                                if (responseCheck.getStatus() == ResponseStatus.SUCCESS) {
                                    TicketShell ticketShell = new TicketShell(new CreateTicket().create(0),
                                            sender.getClientPort(),
                                            login,
                                            password,
                                            command.getCommand().getName());
                                    sender.send(ticketShell);
                                }
                            }
                            ResponseShell responseShell = (ResponseShell) receiver.receiveObject();
                            System.out.println(responseShell.getMessage());
                            if (responseShell.getStatus() == ResponseStatus.SUCCESS)
                                Collection.setTickets(responseShell.getCollection());
                        }
                    }
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            return "File not found";
        } finally {
            scripts.clear();
            return "";
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
