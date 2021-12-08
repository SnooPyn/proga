package utilites;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * read from file
 *
 * @author Jack
 */
public class ReaderFromFile {
    static WriterToFile writer = new WriterToFile();
    String whyFailed = "";
    static boolean was = false;

    public String whyFailed() {
        return whyFailed;
    }

    /**
     * @param filename
     * @return file's content
     * @throws IOException in case if the program can't find file
     */
    public String read(String filename) throws IOException {
        if (was) {
            whyFailed += "The server is working with a different collection.\n";
            return null;
        } else {
            writer.setFilename(filename);
            was = true;
            try {
                String data = "";
                FileInputStream file = new FileInputStream(filename);
                InputStream elem = new BufferedInputStream(file);
                while (elem.available() > 0)
                    data += (char) elem.read();
                file.close();
                return data;
            } catch (java.io.FileNotFoundException e) {
                if (filename.equals(""))
                    whyFailed = "The file name is not specified. The collection is empty.\n";
                else whyFailed = "The specified file was not found. The collection is empty.\n";
                return null;
            }
        }
    }
}