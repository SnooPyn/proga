package connection;

import dataBase.TicketDataBase;
import dataBase.UsersDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseController {


    private Connection connection;
    private UsersDataBase dbUsers;
    private static TicketDataBase db;

    public DataBaseController(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        dbUsers = new UsersDataBase(connection);
        db = new TicketDataBase(connection);
    }

    protected UsersDataBase getUserDataBase() {
        return dbUsers;
    }

    public static TicketDataBase getDataBase() {
        return db;
    }
}