package Controller;

import java.io.IOException;

/**
 * The Command executes the assignment, being used by the Receiver.
 * It's an object whose role is to store all the information required for executing an action.
 */
public interface Commandable {
    String execute(Object o) throws IOException;
    String getName();
}