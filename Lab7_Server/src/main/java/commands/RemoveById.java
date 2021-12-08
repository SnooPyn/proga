package commands;

import controller.*;
import ticket.Ticket;

import java.util.Iterator;
/**
 * The type Remove.
 */
public class RemoveById implements Commandable, CommandWithLogin {
    transient private String username;

    @Override
    public String execute(Object arg) {
        try {
            int id = Integer.parseInt((String) arg);
            Iterator<Ticket> it = Collection.getTickets().iterator();
            while (it.hasNext()) {
                Ticket ticket = it.next();
                if (id == ticket.getId()) {
                    if (ticket.getOwner().equals(username)) {
                        it.remove();
                        return "Ticket is removed.";
                    } else return "Permission denied.";
                }
            }
            return "There are no such element";
        } catch (NumberFormatException exp) {
            return "Value should be\"long\".";

        }

    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}