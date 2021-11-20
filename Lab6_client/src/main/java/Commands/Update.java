package Commands;

import controller.CommandWithObject;

/**
 * The type Update.
 */
public class Update implements CommandWithObject {
    transient int id;
    transient String whyFailed;

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
        return null;
    }
}