package commands;


import controller.*;
import ticket.Ticket;

/**
 * The type Update.
 */
public class Update implements CommandWithObject, CommandWithLogin {
    transient int id;
    transient String whyFailed;
    transient String username;

    @Override
    public String execute(Object arg) {
        Ticket ticketToDelete = Collection.getTickets()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .get();
        Ticket ticket = (Ticket) arg;
        ticket.setId(id);
        ticket.setOwner(username);
        Collection.add(ticket);
        Collection.remove(ticketToDelete);
        return ("Ticket [id:" + arg + "] is updated.");
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public boolean check(int arg) {
        this.id = arg;
        if (Collection.isIdBusy(id))
            if (username.equals(Collection.findById(id).getOwner()))
                return true;
            else {
                whyFailed = "Permission denied";
                return false;
            }
        else {
            whyFailed = "There is no element with this id";
            return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public void setUsername(String login) {
        this.username = login;
    }
}