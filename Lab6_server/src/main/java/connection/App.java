package connection;

import Commands.Exit;
import controller.CommandWithObject;
import controller.Commandable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    static ServerReceiver receiver;
    static ServerSender sender;

    public App(ServerSender sender, ServerReceiver receiver) {
        App.receiver = receiver;
        App.sender = sender;
        System.out.println("App is running on " + receiver.getPort() + " port.\n");
        checkForSaveCommand();
    }

    public void begin() throws IOException, ClassNotFoundException {
        try {
            sender.setPort(receiver.receive());
            System.out.println("Client [" + sender.getPort() + "] connected to server.\n");
        } catch (Exception e) {
            this.begin();
            this.run();
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        try {
            while (true) {
                String commandResult = "";
                int port = Integer.parseInt(receiver.receive());
                if (port != -1)
                    sender.setPort(port);
                else {
                    this.begin();
                    this.run();
                }
                ArrayList commandAndArgument = receiver.receiveCommand();
                Commandable command = (Commandable) commandAndArgument.get(0);
                String arg = (String) commandAndArgument.get(1);
                System.out.println("Command \"" + command.getName() + "\" received.");
                try {
                    CommandWithObject commandWithObject = (CommandWithObject) command;
                    try {
                        if (commandWithObject.check((arg == null) ? 0 : Integer.parseInt(arg))) {
                            sender.send("newObject");
                            commandResult = command.execute(receiver.receiveTicket());
                        } else {
                            sender.send("nope");
                            commandResult = commandWithObject.whyFailed();
                            System.out.println(commandResult);
                        }
                    } catch (Exception e) {
                        commandResult = "The argument should be a number";
                    }
                } catch (Exception e) {
                    commandResult = command.execute(arg);
                }
                sender.send(commandResult);
                if (!commandResult.isEmpty())
                    System.out.println("Result was sent to the client [" + sender.getPort() + "].\n");
                else System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            receiver.receive();
            this.begin();
            this.run();

        }
    }


    public static void checkForSaveCommand() {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            new Exit().execute(null);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}


