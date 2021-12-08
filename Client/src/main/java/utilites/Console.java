package utilites;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Console {
    private static final Scanner in = new Scanner(System.in);

    public static int getClientPort() {
        int port = -10;
        try {
            while (port == -10) {
                System.out.print("Enter the client port:\n$ ");
                String s = in.nextLine();
                port = Integer.parseInt(s);
                if (port > 0 || port < 65536) return port;
                else return getClientPort();
            }
            return port;
        } catch (Exception e) {
            return getClientPort();
        }
    }

    public String getMode() throws NoSuchAlgorithmException {
        String s = "";
        while (!s.equals("log") && !s.equals("sign")) {
            System.out.print("log\\sign:\n$ ");
            s = in.nextLine();
        }
        return s;
    }

    public String getLogin() {
        System.out.print("login:\n$ ");
        String login = in.nextLine();
        if (login.equals("")) return getLogin();
        return login;
    }

    public String getPassword() {
        System.out.print("password:\n$ ");
        String password = in.nextLine();
        if (password.equals("")) return getPassword();
        try {
            return Coder.toCode(password);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }
}