package utilites;

import java.io.*;

public class Reader {
    public static String file;

    public static String read(String filename) throws IOException {
        try {
            file = filename;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            String data = "";
            String line = "";
            long startTime = System.currentTimeMillis();
            while ((line = bufferedReader.readLine()) != null) {
                data += line + "\n";
                if (startTime - System.currentTimeMillis() < -1000) {
                    System.out.println("The file is too large.");
                    return data;
                }
            }
            bufferedReader.close();
            return data;
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}