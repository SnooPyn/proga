package commands;

import connection.DataBaseController;
import controller.Collection;
import controller.CommandWithLogin;
import controller.CommandWithObject;
import controller.CommandWithoutArg;
import ticket.Ticket;

import java.sql.SQLException;

/**
 * The type Add.
 */
public class Add implements CommandWithoutArg, CommandWithObject, CommandWithLogin {
    private transient String whyFailed = "";
    private transient String username;

    @Override
    public String execute(Object o) {
        Ticket ticket = (Ticket) o;
        ticket.setOwner(username);
        ticket.setId(Collection.getFreeId());
        Collection.add((Ticket) o);
        try {
            DataBaseController.getDataBase().loadCollection(Collection.getTickets());
        } catch (SQLException e) {
        }
        return ("New ticket added.");

    }

    @Override
    public void setUsername(String login) {
        this.username = login;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean check(int arg) {
        return true;
    }

    @Override
    public String whyFailed() {
        return null;
    }
}