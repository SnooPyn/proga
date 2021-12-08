package commands;

import controller.*;
/**
 * The type Remove.
 */
public class RemoveById implements Commandable, CommandWithLogin {
    transient private String username;

    @Override
    public String execute(Object arg) {
        return null;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}