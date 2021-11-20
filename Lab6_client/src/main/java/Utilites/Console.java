package Utilites;

import java.util.Scanner;

public class Console {
    private static final Scanner in = new Scanner(System.in);

    public static int readPort() {
        String port = "";
        try {
            System.out.print("Port:\n$ ");
            port = in.nextLine();
            if (port.equals(""))  return readPort();
            return Integer.parseInt(port);
        } catch (Exception e) {
            return readPort();
        }
    }
}