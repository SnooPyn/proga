package commands;

import collections.CollectionManager;
import collections.Console;
import exceptions.CollectionIsEmptyException;

/**
 * Command 'print_field_descending_oscars_count'.Print the element of oscars count with descending order.
 */
public class PrintFieldDescendingOscarsCountCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintFieldDescendingOscarsCountCommand(CollectionManager collectionManager){
        super("print_field_descending_oscars_count","вывести значения поля oscarsCount всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize()==0) throw new CollectionIsEmptyException();
            collectionManager.descendingOrder();
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        }
        return true;
    }
}

