package Commands;

import controller.CommandWithObject;
import controller.CommandWithoutArg;

public class RemoveGreater implements CommandWithObject, CommandWithoutArg {
    transient String whyFailed;

    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public boolean check(int arg) {
        return false;

    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}