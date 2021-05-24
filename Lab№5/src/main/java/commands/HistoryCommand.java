package commands;


import collections.Console;
import exceptions.WrongAmountOfElementsException;

/**
 * Command 'history'. It's here just for logical structure.
 */
public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
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
