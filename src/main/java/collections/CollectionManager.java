package collections;

import java.time.LocalDateTime;
import java.util.*;

import data.*;
import exceptions.CollectionIsEmptyException;

public class CollectionManager {
    private NavigableSet<Movie> moviesCollection =  new TreeSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        this.loadCollection();
    }
    /**
     * @return The collecton itself.
     */
    public NavigableSet<Movie> getCollection() {
        return moviesCollection;
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }
    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return moviesCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return moviesCollection.size();
    }

    /**
     * @return The first element of the collection or null if collection is empty.
     */
    public Movie getFirst() {
        if (moviesCollection.isEmpty()) return null;
        return moviesCollection.first();
    }

    /**
     * @return The last element of the collection or null if collection is empty.
     */
    public Movie getLast() {
        if (moviesCollection.isEmpty()) return null;
        return moviesCollection.last();
    }

    /**
     * @param id ID of the movie.
     * @return A movie by his ID or null if movie isn't found.
     */
    public Movie getById(Long id) {
        for (Movie movie : moviesCollection) {
            if (Objects.equals(movie.GetId(), id)) return movie;
        }
        return null;
    }
    /**
     * @param  movieToFind A movie who's value will be found.
     * @return A movie by his value or null if movie isn't found.
     */
    public Movie getByValue(Movie movieToFind) {
        for (Movie movie : moviesCollection) {
            if (movie.equals(movieToFind)) return movie;
        }
        return null;
    }
    /**
     * @return Movie, who has min mpaaRating.
     * @throws CollectionIsEmptyException If collection is empty.
     */
    public String filterLessThanMpaaRating() throws CollectionIsEmptyException {
        if (moviesCollection.isEmpty()) throw new CollectionIsEmptyException();

        Movie minMovie = getFirst();
        for (Movie movie : moviesCollection) {
            if (movie.GetMpaaRating().compareTo(minMovie.GetMpaaRating()) > 0) {
                minMovie = movie;
            }
        }
        return minMovie.toString();
    }
    /**
     * @return Movie, who has max oscars count.
     * @throws CollectionIsEmptyException If collection is empty.
     */
    public String MaxByOscarsCount() throws CollectionIsEmptyException {
        if (moviesCollection.isEmpty()) throw new CollectionIsEmptyException();

        Movie maxMovie = getFirst();
        for (Movie movie : moviesCollection) {
            if (movie.GetOscarsCount() > maxMovie.GetOscarsCount()) {
                maxMovie = movie;
            }
        }
        return maxMovie.toString();
    }
    /**
     * @param mpaaRating mpaaRating to compare by.
     * @return Information about valid movies or empty string, if there's no such movies.
     */
    public void mpaaRatingComparing(MpaaRating mpaaRating) {
        for (Movie movie:moviesCollection){
            if (movie.GetMpaaRating().CompareTo(mpaaRating)<1)
                Console.println(movie.GetMpaaRating());
        }
    }
    /**
     * Adds a new movie to collection.
     * @param movie A movie to add.
     */
    public void addToCollection(Movie movie) {
        moviesCollection.add(movie);
    }

    /**
     * Removes a new movie to collection.
     * @param movie A movie to remove.
     */
    public void removeFromCollection(Movie movie) {
        moviesCollection.remove(movie);
    }
    /**
     * Clears the collection.
     */
    public void clearCollection() {
        moviesCollection.clear();
    }
    /**
     * Sorts the collection.
     */
    public void descendingOrder() {
        moviesCollection = moviesCollection.descendingSet();
        Console.println("Elements of the collection in the descending order:\n");
        for (Movie movie:moviesCollection){
            Console.println(movie.GetOscarsCount());
        }
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public Long generateNextId() {
        if (moviesCollection.isEmpty()) return 1L;
        return moviesCollection.last().GetId() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(moviesCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        moviesCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (moviesCollection.isEmpty()) return "The collection is empty!";

        String info = "";
        for (Movie movie : moviesCollection) {
            info += movie;
            if (movie != moviesCollection.last()) info += "\n\n";
        }
        return info;
    }

}


