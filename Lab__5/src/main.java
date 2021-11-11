import Commands.*;
import Controller.Collection;
import Controller.Invoker;
import Utility.Decoder;
import Utility.Writer;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Bellazreg Anis
 */

/**
 * The Client class's an object that controls the command execution process
* (by specifying what commands to execute and at what stages of the process to execute them)
 * It's the most important, it passes the order to the Invoker.
*/
public class main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String filename = "";
            if (args.length != 0) filename = args[0];
            else {
                filename = "";
                while (filename.equals("")) {
                    System.out.print("Enter filename\n~ ");
                    filename = scanner.nextLine();
                }
            }
            Writer.setFilename(filename);
            Collection.setTickets(Decoder.decodeIntoCollection(filename));
            Collection.setDateOFCreation(java.time.ZonedDateTime.now());
            Invoker commands = new Invoker();
            commands.regist(new Clear(), new Show(), new Exit(), new Update(), new Add(), new Help(), new Info(),
                    new Execute_script(), new Save(), new Remove_by_id(), new Head(), new Print_descending(), new Max_by_event(),
                    new Remove_lower(), new Remove_greater());
            String commandName;
            while (true) {
                System.out.print("$ ");
                commandName = scanner.nextLine();
                if (!commandName.equals(""))
                    System.out.println(commands.executeCommand(commandName));
            }
        } catch (Exception e) {

        }
    }
}
