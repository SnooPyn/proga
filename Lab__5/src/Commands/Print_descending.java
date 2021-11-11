package Commands;

import Controller.Collection;
import Controller.CommandWithoutArg;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;

/**
 * The type Print descending.
 */
public class Print_descending implements CommandWithoutArg {

    @Override
    public String execute(Object o) throws FileNotFoundException {
        String result = "";
        if (Collection.getSize() > 0) {
            PriorityQueue<Ticket> queue = new PriorityQueue<>(Collection.getTickets());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Ticket ticket = queue.peek();
                for (Ticket ticket1 : queue) {
                    if (ticket.compareTo(ticket1) < 0) {
                        ticket = ticket1;
                    }
                }
                result += (ticket.toString() + "\n---------------------------\n");
                queue.remove(ticket);
            }
            return result;
        } else return "Collection is empty.";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}