package collections;

import java.io.*;
import java.util.Collection;
import java.util.TreeSet;
import java.util.NoSuchElementException;
import java.lang.reflect.Type;

import com.company.Movie;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;



public class FileManager {
    private Gson gson = new Gson();
    private String argcomstr;

    public FileManager(String argcomstr) {
        this.argcomstr = argcomstr;
    }

    public FileManager(String[] args) {
    }

    public void writeCollection(Collection<?> collection) {
        if (argcomstr != null) {
            try (PrintWriter collectionFileWriter = new PrintWriter(new File(argcomstr))) {
                collectionFileWriter.write(gson.toJson(collection));
                Console.println("Collection successful saved to file!");
            } catch (IOException exception) {
                Console.printerror("The boot file is a directory/cannot be opened!");
            }
        } else Console.printerror("System variable with boot file not found!");
    }

    /**
     * Reads collection from a file.
     * @return Readed collection.
     */

    public TreeSet<Movie> readCollection() {
        if (argcomstr != null) {
            try {
                BufferedReader collectionFileBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(argcomstr)));
                TreeSet<Movie> collection;
                Type collectionType = new TypeToken<TreeSet<Movie>>() {}.getType();
                collection = gson.fromJson(collectionFileBufferedReader.readLine().trim(), collectionType);
                Console.println("Collection successfully loaded!");
                return collection;
            } catch (FileNotFoundException exception) {
                Console.printerror("Boot file not found!");
            } catch (NoSuchElementException|IOException exception) {
                Console.printerror("Boot file is empty!");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerror("The required collection was not found in the boot file!");
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        } else Console.printerror("System variable with boot file not found!");
        return new TreeSet<Movie>();
    }

    @Override
    public String toString() {
        String string = "FileManager (class for working with the boot file)";
        return string;
    }
}



