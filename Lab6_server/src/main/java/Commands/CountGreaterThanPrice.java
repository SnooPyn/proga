package Commands;

import controller.Collection;
import controller.Commandable;

public class CountGreaterThanPrice implements Commandable {

    @Override
    public String execute(Object o) {
        int arg = Integer.parseInt((String) o);
        return (Collection.getSize() == 0) ?
                "Collection is empty." :
                String.valueOf(
                        Collection.getTickets()
                                .stream()
                                .filter(ticket -> ticket.getPrice() > arg)
                                .count()
                );
    }

    @Override
    public String getName() {
        return "count_greater_than_price";
    }
}