package Commands;

import Controller.Collection;
import Controller.CommandWithoutArg;
import Utility.Writer;

/**
 * The type Save.
 */
public class Save implements CommandWithoutArg {

    @Override
    public String execute(Object o) {
        Writer.writeLabToFile(Collection.getTickets());
        return ("Collection is saved.");
    }

    @Override
    public String getName() {
        return "save";
    }
}