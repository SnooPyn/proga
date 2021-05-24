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
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        MpaaRating comparingEnum;
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            switch (argument.toUpperCase()){
                case ("G"):
                    comparingEnum = MpaaRating.G;
                    break;
                case ("PG"):
                    comparingEnum = MpaaRating.PG;
                    break;
                case ("PG_13"):
                    comparingEnum = MpaaRating.PG_13;
                    break;
                case ("R"):
                    comparingEnum = MpaaRating.R;
                break;
                case ("NC_17"):
                    comparingEnum = MpaaRating.NC_17;
                    break;
                default:
                    throw new WrongInputEnumException();
            }
            collectionManager.mpaaRatingComparing(comparingEnum);
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (WrongInputEnumException exception){
            Console.printerror("You wrote the wrong argument of the command. Please choose the argument among the variables of MpaaRating!");
        }

        return true;
    }
}
