package commands;

import collections.*;
import exceptions.*;
import com.company.*;


/**
 * Command 'filter_less_than_mpaa_rating mpaaRating'. Prints the element of the collection with lower value than mpaaRating.
 */

public class FilterLessThanMpaaRatingCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterLessThanMpaaRatingCommand(CollectionManager collectionManager) {
        super("filter_less_than_mpaa_rating mpaaRating", "вывести элементы, значение поля mpaaRating которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        MpaaRating comparingEnum;
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            try {
                comparingEnum = MpaaRating.valueOf(argument.toUpperCase());
                collectionManager.mpaaRatingComparing(comparingEnum);
            } catch (EnumConstantNotPresentException E) {
                Console.println("This enum doesn't exist");
            }
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return true;
    }
}
