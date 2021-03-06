package commands;


import com.company.*;
import exceptions.*;
import collections.*;


/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            Movie movieToRemove = collectionManager.getById(id);
            if (movieToRemove == null) throw new MovieNotFoundException();
            collectionManager.removeFromCollection(movieToRemove);
            movieToRemove.DecreaseId();
            Console.println("The movie was successfully deleted!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("The ID must be represented by a number!");
        } catch (MovieNotFoundException exception) {
            Console.printerror("The movie with this ID is not in the collection!");
        }
        return false;
    }
}
