package Commands;

import controller.CommandWithoutArg;

import java.io.FileNotFoundException;


/**
 * The type Print descending.
 */
public class PrintDescending implements CommandWithoutArg {
    transient StringBuilder sb = new StringBuilder();

    @Override
    public String execute(Object o) throws FileNotFoundException {
        return null;
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}