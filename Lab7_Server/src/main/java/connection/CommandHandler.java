package connection;

import controller.*;
import shells.TicketShell;
import shells.RequestShell;
import shells.ResponseShell;
import state.ResponseStatus;

public class CommandHandler extends Thread {
    private ServerReceiver receiver;
    private ServerSender sender;

    private RequestShell request;

    public CommandHandler(RequestShell request, ServerReceiver receiver, ServerSender serverSender) {
        this.request = request;
        this.sender = serverSender;
        this.receiver = receiver;
    }


    @Override
    public void run() {
        try {
            sender.setPort(request.getPort());
            Commandable command = request.getCommand();
            String arg = request.getArgument();
            System.out.println(" Command received\"" + command.getName() + "\" from the user \"" + request.getLogin() + "\"");
            if (command instanceof CommandWithLogin) {
                CommandWithLogin commandWithLogin = (CommandWithLogin) command;
                commandWithLogin.setUsername(request.getLogin());
            }
            if (command instanceof CommandWithObject) {
                CommandWithObject commandWithObject = (CommandWithObject) command;
                boolean checkingResult = arg == null ?
                        true:
                        commandWithObject.check(Integer.parseInt(arg)) ;
                if (checkingResult) {
                    sender.setPort(request.getPort());
                    sender.send(new ResponseShell(ResponseStatus.SUCCESS));
                    TicketShell ticketShell = null;
                    while (ticketShell == null)
                        ticketShell = receiver.getReceivedCityShellForUser(request.getLogin(), request.getCommand().getName());
                    sender.send(new ResponseShell(ResponseStatus.SUCCESS, command.execute(ticketShell.getTicket()), Collection.getTickets()));
                } else
                    sender.send(new ResponseShell(ResponseStatus.UNSUCCES, commandWithObject.whyFailed(), null));
            } else
                sender.send(new ResponseShell(ResponseStatus.SUCCESS, command.execute(arg), Collection.getTickets()));
            System.out.println("The command is executed!");
            this.interrupt();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
