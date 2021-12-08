package commands;

import controller.*;

/**
 * The type Add.
 */
public class Add implements CommandWithoutArg, CommandWithObject, CommandWithLogin {
    private transient String whyFailed = "";
    private transient String username;

    @Override
    public String execute(Object o) {
        return null;

    }

    @Override
    public void setUsername(String login) {
        this.username = login;
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