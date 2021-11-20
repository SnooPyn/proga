package Commands;

import controller.Collection;
import controller.CommandWithoutArg;
import ticket.Ticket;

/**
 * The type Show.
 */
public class Show implements CommandWithoutArg {
    @Override
    public String execute(Object o) {
        if (Collection.getSize() == 0) return ("Collection is empty.");
        else {
            String result = "---------------------------\n";
            for (Ticket ticket : Collection.getTickets())
                result += (ticket.toString()) + "\n---------------------------\n";
            return result;
        }
    }

    @Override
    public String getName() {
        return "show";
    }
}