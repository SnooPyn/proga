package commands;

import exceptions.*;
import collections.*;


/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
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
