package commands;

import java.time.LocalDateTime;
import collections.*;
import exceptions.*;
import data.*;



/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the minimal one.
 */
public class AddIfMinCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MovieAsker movieAsker;

    public AddIfMinCommand(CollectionManager collectionManager, MovieAsker movieAsker) {
        super("add_if_min {element}", "добавить новый элемент, если его значение меньше, чем у наименьшего");
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
            } else Console.printerror("The value of the film is greater than the value of the smallest of the films!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}

