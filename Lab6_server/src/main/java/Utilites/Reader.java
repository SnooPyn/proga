package Utilites;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static String filepath;
    public static String getFilePath() {
        return filepath;
    }
    public static String readFromFile(String filename) throws IOException {
        filepath = filename;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            } catch (FileNotFoundException | NullPointerException e) {
                return null;
            } finally {
                br.close();
            }
        }catch (FileNotFoundException e ){
            System.out.println("File not found");
            return null;
        }
    }
}