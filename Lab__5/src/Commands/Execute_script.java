package Commands;

import Controller.Commandable;
import Controller.Invoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Execute script.
 */
public class Execute_script implements Commandable {
    /**
     * The Ex scr history.
     */
    static ArrayList<String> exScrHistory = new ArrayList<>();

    /**
     * @param arg file name
     */

    @Override
    public String execute(Object arg) throws IOException {
        try {
            exScrHistory.add("execute_script " + arg);
            String result = "";
            File file = new File((String) arg);
            Scanner in = new Scanner(file);
            Invoker commands = new Invoker();
            String exFileName = "";
            while (in.hasNextLine()) {
                String command = in.nextLine();
                String[] exArg = command.split(" ");
                if (exArg.length == 2) {
                    exFileName = exArg[1];
                }
                if (!(command.equals(""))) {
                    if (!(command.equals("execute_script " + exFileName))) {
                        result += ("Command \"" + command + "\":\n");
                        result += commands.executeCommand(command);
                        result += "\n";
                    } else {
                        if (exScrHistory.contains("execute_script " + exFileName)) {
                            result += ("Command \"" + command + "\": is not feasible in order to avoid infinite recursion.\n\n");
                        } else {
                            exScrHistory.add("execute_script " + exFileName);
                            result += ("Executing a nested script \"" + command + "\":\n");
                            result += commands.executeCommand(command);
                            result += "The execution of the nested script is completed.\n\n";
                        }
                    }
                }
            }
            exScrHistory.clear();
            return result;
        } catch (NullPointerException | FileNotFoundException e) {
            return ("File not found.");
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}