package commands;

import controller.*;

/**
 * The type Update.
 */
public class Update implements CommandWithObject, CommandWithLogin {
    transient int id;
    transient String whyFailed;
    transient String username;

    @Override
    public String execute(Object arg) {
        return null;
    }

    @Override
    public String getName() {
        return "update";
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
        this.username = login;
    }
}