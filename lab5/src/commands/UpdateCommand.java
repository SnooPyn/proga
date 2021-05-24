package commands;

import java.time.LocalDateTime;

import exceptions.*;
import collections.*;
import com.company.*;

/**
 * Command 'update'. Updates the information about selected movie.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MovieAsker movieAsker;

    public UpdateCommand(CollectionManager collectionManager, MovieAsker movieAsker) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            Movie oldMovie = collectionManager.getById(id);
            if (oldMovie == null) throw new MovieNotFoundException();

            String name = oldMovie.GetName();
            Coordinates coordinates = oldMovie.GetCoordinates();
            LocalDateTime creationDate = oldMovie.GetCreationDate();
            long oscarsCount = oldMovie.GetOscarsCount();
            long totalBoxOffice = oldMovie.GetTotalBoxOffice();
            MovieGenre genre = oldMovie.GetGenre();
            MpaaRating mpaaRating = oldMovie.GetMpaaRating();
            Person screenwriter = oldMovie.GetScreenwriter();

            collectionManager.removeFromCollection(oldMovie);

            if (movieAsker.askQuestion("Want to change the film's name?")) name = movieAsker.askName();
            if (movieAsker.askQuestion("Want to change the film's coordinates?")) coordinates = movieAsker.askCoordinates();
            if (movieAsker.askQuestion("Want to change the film's oscars?")) oscarsCount = movieAsker.askOscarsCount();
            if (movieAsker.askQuestion("Want to change the film's genre")) genre = movieAsker.askGenre();
            if (movieAsker.askQuestion("Want to change the film's box office?")) totalBoxOffice = movieAsker.askBoxOffice();
            if (movieAsker.askQuestion("Want to change the film's rating?")) mpaaRating = movieAsker.askMpaaRating();
            if (movieAsker.askQuestion("Want to change the film's screenwriter?")) screenwriter = movieAsker.askPerson();

            collectionManager.addToCollection(new Movie(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    oscarsCount,
                    totalBoxOffice,
                    genre,
                    mpaaRating,
                    screenwriter
            ));
            Console.println("Film successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("The ID must be represented by a number!");
        } catch (MovieNotFoundException exception) {
            Console.printerror("There is no film with this ID in the collection!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}