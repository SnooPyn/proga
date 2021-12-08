package dataBase;


import controller.Collection;
import ticket.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.PriorityQueue;

public class TicketDataBase {
    private static Statement statement;
    private static Connection connection;

    public TicketDataBase(Connection connection) throws SQLException {
        TicketDataBase.connection = connection;
        TicketDataBase.statement = connection.createStatement();
        this.createTicketsDB();
        this.createDateCreation();
        Collection.setCreationDate(this.getDateCreation());
    }

    private LocalDate getDateCreation() throws SQLException {
        return LocalDate.now();
    }

    public static long getId() throws SQLException {
        String sql = " SELECT nextval('id_sequence')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            long id = rs.getLong(1);
            System.out.println(id);
            return id;
        }
        return 0;
    }

    private void createDateCreation() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS datecreation (dateCreation text NOT NULL)";
            statement.execute(sql);
            String sql1 = "INSERT INTO datecreation (dateCreation) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, LocalDate.now().toString());
            preparedStatement.execute();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }


    public void loadCollection(PriorityQueue<Ticket> tickets) throws SQLException {
        String sql = "TRUNCATE tickets;";
        statement.execute(sql);
        for (Ticket ticket : tickets) {
            insertTicket(ticket);
        }
        System.out.println("Collection updated");
    }

    public void createTicketsDB() throws SQLException {
        try {
            try {
                String createSequence = "CREATE SEQUENCE IF NOT EXISTS id_sequence start 1 increment 1;";
                statement.execute(createSequence);
            } catch (Exception e) {
            }
            String createTableSQL = "CREATE TABLE IF NOT EXISTS tickets " +
                    "(id BIGINT PRIMARY KEY NOT NULL ," +
                    " username VARCHAR(255) NOT NULL , " +
                    " name VARCHAR(255) NOT NULL , " +
                    " x BIGINT  NOT NULL , " +
                    " y DOUBLE PRECISION NOT NULL , " +
                    " creation_date TEXT NOT NULL , " +
                    " price DOUBLE PRECISION NOT NULL , " +
                    " type VARCHAR(25), " +
                    " id_event BIGINT NOT NULL , " +
                    " name_event VARCHAR(255), " +
                    " date_event VARCHAR(55) NOT NULL , " +
                    " type_event VARCHAR(255) NOT NULL)";
            statement.execute(createTableSQL);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public PriorityQueue<Ticket> getCollection() {
        try {
            PriorityQueue<Ticket> tickets = new PriorityQueue<>();
            String sql = " SELECT * FROM tickets";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setOwner(rs.getString("username"));
                ticket.setName(rs.getString("name"));
                Coordinates coordinates = new Coordinates();
                coordinates.setX(rs.getLong("x"));
                coordinates.setY(rs.getDouble("y"));
                ticket.setCoordinates(coordinates);
                ticket.setCreationDate(ZonedDateTime.parse(rs.getString("creation_date")));
                ticket.setPrice(rs.getDouble("price"));
                ticket.setType(TicketType.valueOf(rs.getString("type")));
                Event event = new Event(ticket);
                event.setId((rs.getInt("id_event")));
                event.setName((rs.getString("name_event")));
                event.setDate(LocalDate.parse(rs.getString("date_event")));
                event.setEventType(EventType.valueOf(rs.getString("type_event")));
                ticket.setEvent(event);

                tickets.add(ticket);
            }
            return tickets;
        } catch (Exception e) {
//            e.printStackTrace();
            return new PriorityQueue<Ticket>();
        }
    }

    private void insertTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (id, username, name, x, y, creation_date, price, type, id_event, name_event, " +
                "date_event, type_event) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, ticket.getId());
        preparedStatement.setString(2, ticket.getOwner());
        preparedStatement.setString(3, ticket.getName());
        preparedStatement.setLong(4, ticket.getCoordinates().getX());
        preparedStatement.setDouble(5, ticket.getCoordinates().getY());
        ZonedDateTime zonedDateTime = ticket.getCreationDate();
        preparedStatement.setString(6, ticket.getCreationDate().toString());
        preparedStatement.setDouble(7, ticket.getPrice());
        preparedStatement.setString(8, ticket.getType().toString());
        preparedStatement.setLong(9, ticket.getEvent().getId());
        preparedStatement.setString(10, ticket.getEvent().getName());
        preparedStatement.setString(11, ticket.getEvent().getDate().toString());
        preparedStatement.setString(12, ticket.getEvent().getEventType().toString());
        preparedStatement.execute();
    }
}
