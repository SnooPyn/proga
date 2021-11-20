package Commands;

import controller.Collection;
import controller.CommandWithObject;
import controller.CommandWithoutArg;
import Utilites.CreateTicket;
import ticket.Ticket;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class RemoveLower implements CommandWithObject, CommandWithoutArg {
    transient String whyFailed;

    @Override
    public String execute(Object o) {
        Ticket newTicket = (Ticket) o;
        List<Ticket> newTickets = Collection.getTickets().stream().filter(ticket -> ticket.compareTo(newTicket) < 0).collect(Collectors.toList());
        Collection.setTickets(new PriorityQueue(newTickets));
        return "All lower tickets were removed";
    }

    @Override
    public boolean check(int arg) {
        if (Collection.getSize() != 0)
            return true;
        whyFailed = "Collection is empty";
        return false;
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}