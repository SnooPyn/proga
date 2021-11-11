package Commands;

import Controller.CommandWithoutArg;

import java.io.FileNotFoundException;

/**
 * The type Exit.
 */
public class Exit implements CommandWithoutArg {

    @Override
    public String execute(Object o) throws FileNotFoundException {
        System.exit(0);
        return null;
    }

    @Override
    public String getName() {
        return "exit";
    }
}