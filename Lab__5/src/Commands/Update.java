package Commands;

import Controller.Collection;
import Controller.CommandWithObject;
import Utility.CreateTicket;
import ticket.Ticket;

/**
 * The type Update.
 */
public class Update implements CommandWithObject {

    @Override
    public String execute(Object arg) {
        if (Collection.isIdBusy(Integer.parseInt((String) arg))) {
            int id = Integer.parseInt((String) arg);
            Ticket ticketToDelete = Collection.getTickets().stream().filter(x -> x.getId() == id).findFirst().get();
            Ticket ticket = null;
            ticket = new CreateTicket().create(id);
            ticket.setId(Integer.parseInt((String) arg));
            Collection.add(ticket);
            Collection.remove(ticketToDelete);
            return ("Ticket [id:" + arg + "] is updated.");
        } else return ("Ticket with such an id does not exist.");
    }

    @Override
    public String getName() {
        return "update";
    }
}