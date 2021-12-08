import connection.App;
import connection.ServerReceiver;
import connection.ServerSender;
import org.postgresql.util.PSQLException;
import java.net.BindException;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Main {
    private static String host = "pg";
    private static Integer port = 5432;
    private static String schema = "studs";
    private static String user = "******";
    private static String password = "*****";
    private static ServerSender sender = new ServerSender(48211);
    private static ServerReceiver receiver = new ServerReceiver(54221);
    public static void main(String[] args) {
        try {

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + schema;
            try {
                App app = new App(sender, receiver, url, user, password);
                app.start();
            }catch (UnknownHostException | PSQLException e) {
                System.out.print("Unable to access data base. ");
                System.out.println("Set values again. ");
                host = readString("host", host);
                port = readInt("port", port);
                schema = readString("schema", schema);
                user = readString("user", user);
                password = readString("password");
                main(new String[]{});
            }
        }  catch (BindException e) {
            System.out.println("Port is busy");
        } catch (Exception e) {
            main(new String[]{});
        }
    }


    private static Scanner scanner = new Scanner(System.in);

    private static String readString(String title, String value) {
        String s = "";
        System.out.print("Enter " + title + " [current value: " + value + "]:\n$ ");
        s = scanner.nextLine();
        if (s.equals("")) return value;
        return s;
    }

    private static String readString(String title) {
        String s = "";
        while (s.equals("")) {
            System.out.print("Enter " + title + ":\n$ ");
            s = scanner.nextLine();
        }
        return s;
    }

    private static Integer readInt(String title, int value) {
        String s = "";
        System.out.print("Enter " + title + " [current value: " + value + "]:\n$ ");
        s = scanner.nextLine();
        if (s.equals("")) return value;
        try {
            return Integer.parseInt(s);
        }
        catch (Exception e){
            System.out.println("Incorrect value");
            return readInt(title, value);
        }
    }
}