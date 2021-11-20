package Commands;


import Utilites.ReaderFromFile;
import connection.App;
import connection.ClientReceiver;
import connection.ClientSender;
import controller.ClientCommand;
import controller.Commandable;
import controller.Commands;
import ticket.CreateTicket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ExecuteScript implements Commandable, ClientCommand {
    String name = "execute_script";
    static private HashMap scripts = new HashMap();

    /**
     * executes script with the name written in command line
     * executes script's name and script's names writen in this script are saved int static variable to prevent collisions
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        ClientReceiver receiver = App.getReceiver();
        ClientSender sender = App.getSender();
        scripts.put((String) arg, 1);
        try {
            String data = ReaderFromFile.read((String) arg);
            Commands command = new Commands();
            if (data != null) {
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    String commandName = commands[i];
                    System.out.println("Команда \"" + commandName + "\":");
                    System.out.print("\t");
                    command.setCommand(commandName);
                    if (command.getMessage() != (null))
                        System.out.println(command.getMessage());
                    if (command.getCommand() != null) {
                        try {
                            try {
                                ClientCommand clientCommand = (ClientCommand) command.getCommand();
                                System.out.println(command.getCommand().execute(command.getArg()).replace("\n", "\n\t"));
                            } catch (ClassCastException e) {
                                if (command.getCommand().getName().equals("exit")) {
                                    sender.sendCommand(command);
                                    command.getCommand().execute(command.getArg());
                                } else {
                                    sender.sendCommand(command);
                                    if (sender.isCommandWithObject())
                                        if (receiver.receive().equals("newObject")) {
                                            sender.send(new CreateTicket().create(0));
                                        }
//                                  }
                                    System.out.println(receiver.receive().replace("\n", "\n\t"));
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Unable to access");
                        }
                    }
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            return "Указанный файл не найден";
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
