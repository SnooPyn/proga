package commands;


import controller.*;

public class Clear implements CommandWithoutArg {


    @Override
    public String execute(Object o) {
        if (Collection.getSize() == 0) return "Collection is empty.";
        Collection.clear();
        return ("Collection has been successfully cleared.");
    }

    @Override
    public String getName() {
        return "clear";
    }
}
