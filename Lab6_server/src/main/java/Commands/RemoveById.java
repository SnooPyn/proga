package Commands;

import controller.Collection;
import controller.Commandable;
import ticket.Ticket;

import java.util.Iterator;

/**
 * The type Remove.
 */
public class RemoveById implements Commandable {

    @Override
    public String execute(Object arg) {
        if (Collection.getSize() == 0) return ("Collection is empty.");
        else {
            int id = 0;
            try {
                id = Integer.parseInt((String) arg);
            } catch (NumberFormatException exp) {
            }

            Iterator<Ticket> it = (Iterator<Ticket>) Collection.getTickets().iterator();
            while (it.hasNext()) {
                Ticket ticket = (Ticket) it.next();
                long Id = ticket.getId();
                if (id == Id) {
                    it.remove();
                    return ("Ticket is removed.");
                }
            }
            return ("There is no element with this id.");
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}