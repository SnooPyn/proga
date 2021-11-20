package Commands;

import controller.Commandable;

/**
 * The type Remove.
 */
public class RemoveById implements Commandable {

    @Override
    public String execute(Object arg) {
        return null;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}