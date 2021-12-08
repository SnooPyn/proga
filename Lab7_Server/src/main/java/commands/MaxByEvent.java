package commands;

import controller.*;
import ticket.*;

public class MaxByEvent implements CommandWithoutArg {


    @Override
    public String execute(Object o) {
        return (Collection.getSize() == 0) ? "Collection is empty." :
                Collection.findById(Collection.getTickets()
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