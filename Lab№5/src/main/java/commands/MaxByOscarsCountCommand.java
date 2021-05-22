package commands;

import collections.CollectionManager;
import collections.Console;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;

/**
 * Command 'max_by_oscars_count'. Prints the element of the collection with maximum oscars count.
 */
public class MaxByOscarsCountCommand extends AbstractCommand{
    private CollectionManager collectionManager;


    public MaxByOscarsCountCommand(CollectionManager collectionManager){
        super("max_by_oscars_count","вывести любой объект из коллекции, значение поля oscarsCount которого является максимальным");
        this.collectionManager = collectionManager;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println(collectionManager.MaxByOscarsCount());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        }
        return true;
    }
}
