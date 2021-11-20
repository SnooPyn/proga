package controller;

import ticket.Ticket;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

public class Collection implements Serializable {
    private static PriorityQueue<Ticket> collection = new PriorityQueue<Ticket>();
    private static ZonedDateTime DateOFCreation;

    private static int freeId = 1;

    public static void setDateOFCreation(ZonedDateTime dateOFCreation) {
        DateOFCreation = dateOFCreation;
    }

    public static PriorityQueue<Ticket> getTickets() {
        return collection;
    }

    public static void setTickets(PriorityQueue<Ticket> collection) {
        Collection.collection = collection;
    }

    public static int getFreeId() {
        for (Ticket ticket : collection) {
            if (freeId == ticket.getId())
                freeId++;
        }
        return freeId;
    }

    public static int getSize() {
        return collection.size();
    }

    public static void clear() {
        collection.clear();
    }

    public static void remove(Ticket ticket) {
        collection.remove(ticket);
    }

    public static void add(Ticket ticket) {
        collection.add(ticket);
    }

    public static boolean isIdBusy(int id) {
        for (Ticket ticket : collection)
            if (ticket.getId() == id) return true;
        return false;
    }


    public static String getInfo() {
        return "Type: PriorityQueue\nSize: " + collection.size() +
                "\nCreation date: " + DateOFCreation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public static boolean isKeyFree(Long ind) {
        try {
            for (Ticket ticket : collection)
                if (ticket.getId() == ind) return false;
            return true;
        } catch (Exception e) {
            return true;

        }
    }

    public static Ticket getById(long id) {
        for (Ticket ticket : collection)
            if (ticket.getId() == id) return ticket;
        return null;
    }
}