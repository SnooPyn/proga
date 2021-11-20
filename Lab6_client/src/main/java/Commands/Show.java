package Commands;

import controller.CommandWithoutArg;

/**
 * The type Show.
 */
public class Show implements CommandWithoutArg {
    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "show";
    }
}