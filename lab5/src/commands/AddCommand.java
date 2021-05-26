package commands;

import java.time.LocalDateTime;
import collections.*;
import exceptions.*;
import com.company.*;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MovieAsker movieAsker;

    public AddCommand(CollectionManager collectionManager, MovieAsker movieAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            collectionManager.addToCollection(new Movie(
                    movieAsker.askName(),
                    movieAsker.askCoordinates(),
                    LocalDateTime.now(),
                    movieAsker.askBoxOffice(),
                    movieAsker.askOscarsCount(),
                    movieAsker.askGenre(),
                    movieAsker.askMpaaRating(),
                    movieAsker.askPerson()
            ));
            Console.println("Movie added successfully!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}