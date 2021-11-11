package Commands;

import Controller.Collection;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import Utility.CreateTicket;
import ticket.Ticket;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Remove_greater implements CommandWithObject, CommandWithoutArg {
    @Override
    public String execute(Object o) {
        Ticket newTicket = new CreateTicket().create(0);
        List<Ticket> newTickets = Collection.getTickets().stream().filter(ticket -> ticket.compareTo(newTicket) > 0).collect(Collectors.toList());
        Collection.setTickets(new PriorityQueue(newTickets));
        return "All greater tickets were removed";
    }

    @Override
    public String getName() {
        return "remove_greater";
    }
}