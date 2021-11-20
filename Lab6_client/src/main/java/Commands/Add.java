package Commands;

import controller.CommandWithObject;
import controller.CommandWithoutArg;

/**
 * The type Add.
 */
public class Add implements CommandWithoutArg, CommandWithObject {

    @Override
    public String execute(Object o) {
        return null;

    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean check(int arg) {
        return true;
    }

    @Override
    public String whyFailed() {
        return null;
    }
}