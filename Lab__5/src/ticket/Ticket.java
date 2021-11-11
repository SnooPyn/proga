package ticket;

import java.time.ZonedDateTime;

public class Ticket implements Comparable {
    private long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Event event; //Поле не может быть null

    public Ticket() {

    }

    public Ticket(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Double price, TicketType type, Event event) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.event = event;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String parseXML() {
        return "\n\t<ticket>" + "\n" +
                "\t\t<id>" + id + "</id>" + "\n" +
                "\t\t<name>" + name + "</name>" + "\n" +
                "\t\t<x>" + coordinates.getX() + "</x>" + "\n" +
                "\t\t<y>" + coordinates.getY() + "</y>" + "\n" +
                "\t\t<creation_date>" + creationDate + "</creation_date>" + "\n" +
                "\t\t<price>" + price + "</price>" + "\n" +
                "\t\t<type>" + type + "</type>" + "\n" +
                "\t\t<id_event>" + event.getId() + "</id_event>" + "\n" +
                "\t\t<name_event>" + event.getName() + "</name_event>" + "\n" +
                "\t\t<date_event>" + event.getDate() + "</date_event>" + "\n" +
                "\t\t<type_event>" + event.getEventType() + "</type_event>" + "\n" +
                "\t</ticket>";
    }

    @Override
    public String toString() {
        return "Ticket (id:" + id + "):\n\tName: " + name + ",\n\tCoordinates:\n\t\tx: " + coordinates.getX() +
                ",\n\t\ty: " + coordinates.getY() + ",\n\tCreation date: " + creationDate + ",\n\tPrice: " + price +
                ",\n\tType: " + type + ",\n\tID event (id:" + event.getId() + "):" + "\n\t\tName event: " + event.getName() +
                ",\n\t\tDate event " + event.getDate() + ",\n\t\tType event: " + event.getEventType() + ".";
    }

    @Override
    public int compareTo(Object o) {
        return (int) (this.getPrice() - ((Ticket) o).getPrice());
    }
}