package commands;

import java.time.LocalDateTime;
import collections.*;
import exceptions.*;
import com.company.*;



/**
 * Command 'add_if_max'. Adds a new element to collection if it's more than the maximal one.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MovieAsker movieAsker;

    public AddIfMaxCommand(CollectionManager collectionManager, MovieAsker movieAsker) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.movieAsker = movieAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Movie movieToAdd = new Movie(
                    collectionManager.generateNextId(),
                    movieAsker.askName(),
                    movieAsker.askCoordinates(),
                    LocalDateTime.now(),
                    movieAsker.askBoxOffice(),
                    movieAsker.askOscarsCount(),
                    movieAsker.askGenre(),
                    movieAsker.askMpaaRating(),
                    movieAsker.askPerson()
            );
            if (collectionManager.collectionSize() == 0 || movieToAdd.compareTo(collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(movieToAdd);
                Console.println("Movie added successfully!");
                return true;
            } else Console.printerror("The value of the film is smaller than the value of the greatest of the films!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
