package collections;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import com.company.*;
import com.google.gson.*;


public class FileManager {
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private final String argcomstr;
    TreeSet<Movie> CollectionMovie = new TreeSet<Movie>();

    public FileManager(String argcomstr) {
        this.argcomstr = argcomstr;
    }

    public void writeCollection(Collection<?> collection) {
        if (argcomstr != null) {
            try (PrintWriter collectionFileWriter = new PrintWriter(new File(argcomstr))) {
                collectionFileWriter.write(gsonBuilder.create().toJson(collection));
                Console.println("Collection successful saved to file!");
            } catch (IOException exception) {
                Console.printerror("The boot file is a directory/cannot be opened!");
            }
        } else Console.printerror("System variable with boot file not found!");
    }

    /**
     * Reads collection from a file.
     *
     * @return Readed collection.
     */

    public TreeSet<Movie> readCollection() {
        if (argcomstr != null) {
            try {
                BufferedReader collectionFileBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(argcomstr)));
                JsonDeserializer<Movie> deserializer = (jsonElement, type, jsonDeserializationContext) -> {
                    JsonObject movieJson = jsonElement.getAsJsonObject();
                    JsonObject personJson = movieJson.getAsJsonObject("Person");
                    Person person = new Person(personJson.get("PersonName").getAsString(),
                            personJson.get("Height").getAsInt(),
                            personJson.get("Weight").getAsDouble());
                    return new Movie(
                            movieJson.get("Name").getAsString(),
                            new Coordinates(movieJson.get("x").getAsFloat(), movieJson.get("y").getAsDouble()),
                            LocalDateTime.now(),
                            movieJson.get("OscarsCount").getAsLong(),
                            movieJson.get("TotalBoxOffice").getAsLong(),
                            MovieGenre.valueOf(movieJson.get("MovieGenre").getAsString()),
                            MpaaRating.valueOf(movieJson.get("MpaaRating").getAsString()), person);
                };
                String text;
                StringBuffer ListText = new StringBuffer();
                while ((text = collectionFileBufferedReader.readLine()) != null)
                    ListText.append(text);
                Gson gson = gsonBuilder.registerTypeAdapter(Movie.class, deserializer).create();
                Movie[] ArrayMovie = gson.fromJson(ListText.toString(), Movie[].class);
                CollectionMovie.addAll(Arrays.asList(ArrayMovie));
                Console.println("Collection successfully loaded!");
            } catch (FileNotFoundException exception) {
                Console.printerror("Boot file not found!");
            } catch (NoSuchElementException | IOException exception) {
                Console.printerror("Boot file is empty!");

            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        } else Console.printerror("System variable with boot file not found!");
        return CollectionMovie;
    }

    @Override
    public String toString() {
        String string = "FileManager (class for working with the boot file)";
        return string;
    }
}



