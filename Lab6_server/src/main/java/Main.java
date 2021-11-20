import Utilites.Decoder;
import Utilites.Writer;
import connection.App;
import connection.ServerReceiver;
import connection.ServerSender;
import controller.Collection;

import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String filename = "";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 0) filename = args[0];
        else {
            while (filename.equals("")) {
                System.out.print("Enter filename\n$ ");
                filename = scanner.nextLine();
            }
        }
        Writer.setFilename(filename);
        Collection.setTickets(Decoder.decodeIntoCollection(filename));
        Collection.setDateOFCreation(java.time.ZonedDateTime.now());
        try {
            ServerSender sender = new ServerSender(45213);
            ServerReceiver receiver = new ServerReceiver(2153);
            App app = new App(sender, receiver);
            receiver.receive();
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("This port is busy");
        } catch (EOFException e) {
            main(new String[]{filename});
        }
    }
}