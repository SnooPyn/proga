package Commands;

import controller.CommandWithObject;
import controller.CommandWithoutArg;

public class RemoveLower implements CommandWithObject, CommandWithoutArg {
    transient String whyFailed;

    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public boolean check(int arg) {
        return false;

    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}