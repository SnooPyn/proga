import connection.*;
import utilites.Console;
import commands.*;
import controller.*;

import java.io.IOException;
import java.net.BindException;
import java.security.NoSuchAlgorithmException;


public class Main {//Client

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        int clientPort = -10;
        int serverPort =  53221;
        if (args.length != 0) {
            try {
                clientPort = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Incorrect port(positive integer, less than 65536).");
            }
        }
        if (clientPort < 0) clientPort = Console.getClientPort();
        Commands commands = new Commands();
        commands.regist(new Add(), new Clear(), new ExecuteScript(), new Exit(),
                new Head(), new Help(), new Info(), new MaxByEvent(), new PrintDescending(), new RemoveById(),
                new RemoveGreater(), new RemoveLower(), new Show(), new Update());
        try {
            ClientReceiver receiver = new ClientReceiver(clientPort);
            ClientSender sender = new ClientSender(serverPort, clientPort);
            App app = new App(sender, receiver);
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("Port is busy");
        } catch (IllegalArgumentException e) {
            System.out.println("Port is too big");
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
        }
    }
}

