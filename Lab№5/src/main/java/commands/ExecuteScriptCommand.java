package commands;

import collections.Console;
import exceptions.WrongAmountOfElementsException;

/**
 * Command 'execute_script'. Executes scripts from a file. Ectually only checks argument and prints messages.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    /**
     * Executes the command, but partially.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Executing a script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
