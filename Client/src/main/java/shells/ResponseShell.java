package shells;

import ticket.Ticket;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.PriorityQueue;

public class ResponseShell implements Serializable {
    private int status;
    private String message;
    PriorityQueue<Ticket> collection;
    private LocalDate creationDate;

    public ResponseShell(int status, String message, PriorityQueue<Ticket> collection) {
        this.status = status;
        this.message = message;
        this.collection = collection;
    }

    public ResponseShell(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return message;
    }

    public void setResult(String result) {
        this.message = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PriorityQueue<Ticket> getCollection() {
        return collection;
    }

    public void setCollection(PriorityQueue<Ticket> collection) {
        this.collection = collection;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}