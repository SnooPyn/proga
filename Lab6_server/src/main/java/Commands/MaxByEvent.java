package Commands;

import controller.Collection;
import controller.CommandWithoutArg;
import ticket.Event;
import ticket.Ticket;

public class MaxByEvent implements CommandWithoutArg {
    @Override
    public String execute(Object o) {
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