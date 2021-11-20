package Commands;

import controller.Collection;
import controller.CommandWithObject;
import ticket.Ticket;

/**
 * The type Update.
 */
public class Update implements CommandWithObject {
    transient int id;
    transient String whyFailed;

    @Override
    public String execute(Object arg) {
        Ticket ticketToDelete = Collection.getTickets().stream().filter(x -> x.getId() == id).findFirst().get();
        Ticket ticket = (Ticket) arg;
        ticket.setId(id);
        Collection.add(ticket);
        Collection.remove(ticketToDelete);
        return ("Ticket [id:" + arg + "] is updated.");
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public boolean check(int arg) {
        this.id = arg;
        if (Collection.isIdBusy(id))
            return true;
        else {
            whyFailed = "Ticket with such an id does not exist.";
            return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}