package Utilites;

import controller.Collection;
import ticket.Ticket;

import java.io.FileWriter;
import java.util.PriorityQueue;

public class Writer {
    static String filename;

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Writer.filename = filename;
    }

    public static void writeLabToFile() {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("<?xml version=\"1.0\"?>");
            writer.write("\n<tickets>\n");
            for (Ticket ticket : Collection.getTickets())
                writer.write(ticket.parseXML() + "\n");
            writer.write("\n</tickets>");
            writer.close();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}