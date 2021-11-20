package Utilites;

import ticket.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateTicket {
    private final Scanner scanner = new Scanner(System.in);
    public static String[] params;

    public static void setParams(String[] params) {
        CreateTicket.params = params;
    }

    public Ticket create(long id) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        this.setNameForTicket(ticket);
        Coordinates coordinates = new Coordinates();
        this.setCoordinateXForCoordinates(coordinates);
        this.setCoordinateYForCoordinates(coordinates);
        ticket.setCoordinates(coordinates);
        this.setPriceForTicket(ticket);
        this.setTicketTypeForTicket(ticket);
        Event event = new Event(ticket);
        this.setNameForEvent(event);
        this.setDateForEvent(event);
        this.setEventTypeForEvent(event);
        ticket.setEvent(event);
        ticket.setCreationDate(ZonedDateTime.now());
        return ticket;
    }


    public void setNameForTicket(Ticket ticket) {
        System.out.println("Enter the ticket name.");
        System.out.print("$ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals("null")) {
            this.setNameForTicket(ticket);
        } else ticket.setName(name);
    }

    public void setCoordinateXForCoordinates(Coordinates coords) {
        try {
            System.out.println("Enter the coordinate x");
            System.out.print("$ ");
            String x = scanner.nextLine();
            Long xn = Long.valueOf(x);
            if (x.equals("")  ||xn > 687) {
                System.out.println("The value must be less than 687");
                this.setCoordinateXForCoordinates(coords);
            } else coords.setX(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("The value must be \"int\".");

            this.setCoordinateXForCoordinates(coords);
        }
    }

    public void setCoordinateYForCoordinates(Coordinates coords) {
        try {
            System.out.println("Enter the coordinate y");
            System.out.print("$ ");
            String y = scanner.nextLine();
            double yn = Double.parseDouble(y);
            if (y.equals("")   || yn > 153) {
                System.out.println("The value must be less than 153");
                this.setCoordinateYForCoordinates(coords);
            } else {
                coords.setY(yn);
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("The value must be \"int\".");
            this.setCoordinateYForCoordinates(coords);
        }
    }

    public void setPriceForTicket(Ticket ticket) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the price");
            System.out.print("$ ");
            String price = scanner.nextLine();
            double pricen = Double.parseDouble(price);
            if (price.equals("") ||   pricen < 0) {
                System.out.println("The value must be more than 0");
                this.setPriceForTicket(ticket);
            } else ticket.setPrice(pricen);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("The value must be \"double\".");
            this.setPriceForTicket(ticket);
        }
    }

    public void setTicketTypeForTicket(Ticket ticket) {
        try {
            System.out.println("Enter type(VIP,USUAL,BUDGETARY,CHEAP)");
            System.out.print("$ ");
            String ticketType = scanner.nextLine().toUpperCase();
            if (ticketType.equals("") ) {
                this.setTicketTypeForTicket(ticket);
            } else if (ticketType.equals("VIP") || ticketType.equals("USUAL") || ticketType.equals("BUDGETARY") || ticketType.equals("CHEAP"))
                ticket.setType(TicketType.valueOf(ticketType));
            else this.setTicketTypeForTicket(ticket);
        } catch (InputMismatchException e) {
            this.setTicketTypeForTicket(ticket);
        }
    }

    public void setNameForEvent(Event event) {
        System.out.println("Enter the event name.");
        System.out.print("$ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals("null")) {
            this.setNameForEvent(event);
        } else event.setName(name);
    }

    public void setDateForEvent(Event event) {
        System.out.println("Enter the time of the event in the format provided: yyyy-mm-dd");
        System.out.print("$ ");
        String date = scanner.nextLine();
        try {
            LocalDate localDate = LocalDate.parse(date);
            event.setDate(localDate);
        } catch (Exception e) {
            System.out.println("Invalid format.");
            this.setDateForEvent(event);
        }
    }

    public void setEventTypeForEvent(Event event) {
        try {
            System.out.println("Enter the event type(E_SPORTS, FOOTBALL, BASKETBALL, EXPOSITION)");
            System.out.print("$ ");
            String eventType = scanner.nextLine().toUpperCase();
            if (eventType.equals("")) {
                this.setEventTypeForEvent(event);
            } else if (eventType.equals("E_SPORTS") || eventType.equals("FOOTBALL") || eventType.equals("BASKETBALL") || eventType.equals("EXPOSITION"))
                event.setEventType(EventType.valueOf(eventType));
            else this.setEventTypeForEvent(event);
        } catch (InputMismatchException e) {
            this.setEventTypeForEvent(event);
        }
    }
}