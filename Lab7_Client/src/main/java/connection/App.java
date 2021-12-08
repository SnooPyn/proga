package connection;

import controller.Collection;
import controller.CommandWithObject;
import controller.Commands;
import utilites.Console;
import utilites.CreateTicket;
import shells.RequestShell;
import shells.ResponseShell;
import shells.TicketShell;
import state.ModeStatus;
import state.ResponseStatus;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class App {
    private static ClientReceiver receiver;
    private static ClientSender sender;
    boolean was = false;
    private Scanner in = new Scanner(System.in);
    private RequestShell authRequest = null;
    private ResponseShell authResponse = null;
    private Console console = new Console();
    private static String login;
    private static String password;
    private int mode;

    public App(ClientSender sender, ClientReceiver receiver) throws NoSuchAlgorithmException {
        App.receiver = receiver;
        App.sender = sender;
    }

    public static ClientSender getSender() {
        return sender;
    }
    public static ClientReceiver getReceiver() {
        return receiver;
    }
    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public void begin() throws IOException {
        try {
            int validStatus = ResponseStatus.NON_VALID;
            if (authRequest == null) {
                while (validStatus == ResponseStatus.NON_VALID) {
                    login = console.getLogin();
                    password = console.getPassword();
                    mode = (console.getMode().equals("log")) ? ModeStatus.AUTHENTICATION : ModeStatus.AUTHORIZATION;
                    authRequest = new RequestShell(sender.getClientPort(), login, password);
                    authRequest.setMode(mode);
                    sender.send(authRequest);
                    authResponse = (ResponseShell) receiver.receiveObject();
                    validStatus = authResponse.getStatus();
                    System.out.println(authResponse.getResult());
                    if (validStatus == ResponseStatus.VALID) {
                        Collection.setTickets(authResponse.getCollection());
                        Collection.setCreationDate(authResponse.getCreationDate());
                    }
                }
                was = true;
            } else {
                sender.send(new RequestShell(sender.getClientPort(), login, password));
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("the server is unavailable now...");
            this.authRequest = null;
            this.was = false;
            this.begin();
        }
    }

    private Commands command = new Commands();

    public void run() throws IOException {
        while (true) {
            System.out.print("$ ");
            String commandName = in.nextLine();
            command.setCommand(commandName);
            if (command.getMessage() != (null))
                System.out.println(command.getMessage());
            if (command.getCommand() != null) {
                try {
                    if (!was) this.begin();
                    if (command.getCommand().getName().equals("execute_script") || command.getCommand().getName().equals("help"))
                        System.out.println(command.getCommand().execute(command.getArg()));
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
                } catch (Exception e) {
//                    e.printStackTrace();
                    if (command.getCommand().getName().equals("show"))
                        Collection.getTickets().forEach(ticket -> System.out.println(ticket + "\n----------------------\n"));
                    System.out.println("the server is unavailable now...");
                    this.run();
                }
            }
        }
    }
}
