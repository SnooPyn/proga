package Commands;

import controller.CommandWithoutArg;

public class MaxByEvent implements CommandWithoutArg {
    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "max_by_event";
    }
}
