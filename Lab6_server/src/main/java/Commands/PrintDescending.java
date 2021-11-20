package Commands;

import controller.Collection;
import controller.CommandWithoutArg;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.util.Comparator;


/**
 * The type Print descending.
 */
public class PrintDescending implements CommandWithoutArg {
    transient StringBuilder sb = new StringBuilder();

    @Override
    public String execute(Object o) throws FileNotFoundException {
        if (Collection.getSize() > 0) {
            Collection.getTickets()
                    .stream()
                    .sorted(Comparator.comparing(Ticket::getPrice).reversed())
                    .forEach(ticket -> sb.append(ticket).append("\n---------------------------\n"));
            return sb.toString();
        } else return "Collection is empty.";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}