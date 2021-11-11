package Commands;

import Controller.Collection;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import Utility.CreateTicket;
import ticket.Ticket;

/**
 * The type Add.
 */
public class Add implements CommandWithoutArg, CommandWithObject {

    @Override
    public String execute(Object o) {
        Ticket ticket = null;
        ticket = new CreateTicket().create(Collection.getFreeId());
        Collection.add(ticket);
        return ("New ticket added.");

    }

    @Override
    public String getName() {
        return "add";
    }
}