package commands;

import controller.*;

public class RemoveLower implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    transient String whyFailed;
    transient String username;

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

    @Override
    public void setUsername(String login) {
        this.username = login;
    }
}
