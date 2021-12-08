package commands;

import connection.DataBaseController;
import controller.Collection;
import controller.CommandWithoutArg;
import dataBase.TicketDataBase;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * The type Exit.
 */
public class Exit implements CommandWithoutArg {

    @Override
    public String execute(Object o) throws FileNotFoundException {
        try {
            DataBaseController.getDataBase().loadCollection(Collection.getTickets());
        } catch (SQLException e) {
        }
        System.out.println("Collection is saved");
        return "Collection saved";
    }

    @Override
    public String getName() {
        return "exit";
    }
}