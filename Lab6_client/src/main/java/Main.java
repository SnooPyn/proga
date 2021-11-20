import Commands.*;
import Utilites.Console;
import connection.App;
import connection.ClientReceiver;
import connection.ClientSender;
import controller.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.util.NoSuchElementException;

/**
 * The type Main.
 */
public class Main {
    private static int clientPort;
    private static int serverPort = 2153;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws FileNotFoundException the file not found exception
     */
    public static void main(String[] args) {
        setPortAndHost(args);
        Commands.regist(new Add(), new Clear(), new ExecuteScript(), new Exit(),
                new Head(), new Help(), new Info(), new MaxByEvent(), new PrintDescending(), new RemoveById(),
                new RemoveGreater(), new RemoveLower(), new Show(), new Update());
        try {
            ClientReceiver receiver = new ClientReceiver(clientPort);
            ClientSender sender = new ClientSender(serverPort, clientPort);
            App app = new App(sender, receiver);
            app.begin();
            app.run();
        } catch (BindException | IllegalArgumentException e) {
            System.out.println("Incorrect port");
        } catch (SocketException e) {
            System.out.println("Incorrect host");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("The program was interrupted");
        }
    }

    public static void setPortAndHost(String[] args) {
        if (args.length != 0) {
            try {
                clientPort = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Port should be a number");
                clientPort = Console.readPort();
            }
        } else clientPort = Console.readPort();
    }
}