package commands;


import exceptions.*;
import collections.*;


/**
 * Command 'help'. It's here just for logical structure.
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "display help for available commands");
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
