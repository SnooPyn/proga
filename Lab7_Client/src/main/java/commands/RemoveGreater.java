package commands;

import controller.*;

public class RemoveGreater implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    transient String whyFailed;
    transient String username;

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

    @Override
    public void setUsername(String login) {

    }
}
