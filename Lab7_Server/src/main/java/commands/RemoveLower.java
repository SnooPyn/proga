package commands;

import controller.*;
import ticket.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveLower implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    transient String whyFailed;
    transient String username;

    @Override
    public String execute(Object o) {
        Ticket newTicket = (Ticket) o;
        List<Ticket> ticketsToRemove = Collection.getTickets()
                .stream()
                .filter(ticket -> ticket.getOwner().equals(username) &&
                        ticket.compareTo(newTicket) < 0)
                .collect(Collectors.toList());
        Collection.getTickets().removeAll(ticketsToRemove);
        return ticketsToRemove.size() == 0
                ? "Not a single item has been deleted"
                : "All lower tickets were removed";
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

    @Override
    public void setUsername(String login) {
        this.username = login;
    }
}