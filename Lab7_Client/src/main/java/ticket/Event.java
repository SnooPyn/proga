package ticket;

import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Comparable, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate date; //Поле не может быть null
    private EventType eventType; //Поле не может быть null

    public Event(Ticket ticket) {
        this.id = ticket.getId();
    }

    public Event(long id, String name, LocalDate date, EventType eventType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.eventType = eventType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String toString() {
        return "Мероприятие (id:" + id + "):" + "\n\t\tИмя: " + name +
                ",\n\t\tДата проведения " + date + ",\n\t\tТип: " + eventType + ".";
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().length() - o.toString().length();
    }
}