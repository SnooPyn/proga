package Commands;

import controller.Collection;
import controller.CommandWithObject;
import controller.CommandWithoutArg;
import ticket.Ticket;

/**
 * The type Add.
 */
public class Add implements CommandWithoutArg, CommandWithObject {

    @Override
    public String execute(Object o) {
        Ticket ticket = (Ticket) o;
        ticket.setId(Collection.getFreeId());
        Collection.add((Ticket) o);
        return ("New ticket added.");

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