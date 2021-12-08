package shells;

import ticket.Ticket;

import java.io.Serializable;

public class TicketShell implements Serializable {
    private Ticket ticket;
    private int port;
    private String login;
    private String password;
    private String commandName;

    public Ticket getTicket() {
        return ticket;
    }

    public int getPort() {
        return port;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCommandName() {
        return commandName;
    }

    public TicketShell(Ticket ticket, int port, String login, String password, String commandName) {
        this.ticket = ticket;
        this.port = port;
        this.login = login;
        this.password = password;
        this.commandName = commandName;
    }
}