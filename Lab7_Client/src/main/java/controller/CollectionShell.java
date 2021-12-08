package controller;

import ticket.Ticket;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.PriorityQueue;

public class CollectionShell implements Serializable {
    PriorityQueue<Ticket> collection;
    LocalDate dateCreation;
    HashMap<String, Long> colors;

    public CollectionShell(PriorityQueue<Ticket> collection, LocalDate dateCreation, HashMap<String, Long> colors) {
        this.collection = collection;
        this.dateCreation = dateCreation;
        this.colors = colors;
    }

    public PriorityQueue<Ticket> getCollection() {
        return collection;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public HashMap<String, Long> getColors() {
        return colors;
    }
}