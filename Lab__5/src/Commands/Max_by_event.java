package Commands;

import Controller.Collection;
import Controller.CommandWithoutArg;
import ticket.Event;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Max_by_event implements CommandWithoutArg {
    @Override
    public String execute(Object o) throws FileNotFoundException, IOException {
        return (Collection.getSize() == 0) ? "Collection is empty." : Collection.getById(Collection.getTickets()
                .stream()
                .map(Ticket::getEvent)
                .max(Event::compareTo)
                .get().getId()
        ).toString();
    }

    @Override
    public String getName() {
        return "max_by_event";
    }
}