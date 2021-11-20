package Commands;

import controller.CommandWithoutArg;
import Utilites.Writer;

import java.io.FileNotFoundException;

/**
 * The type Exit.
 */
public class Exit implements CommandWithoutArg {

    @Override
    public String execute(Object o) throws FileNotFoundException {
        Writer.writeLabToFile();
        System.out.println("Collection saved");
        return "Collection saved";
    }

    @Override
    public String getName() {
        return "exit";
    }
}