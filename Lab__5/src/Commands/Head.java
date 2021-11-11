package Commands;

import Controller.Collection;
import Controller.CommandWithoutArg;

public class Head implements CommandWithoutArg {

    @Override
    public String execute(Object object) {
        return Collection.getTickets().peek() == null ? "Collection is empty" :  Collection.getTickets().peek().toString();
    }


    @Override
    public String getName() {
        return "head";
    }
}
