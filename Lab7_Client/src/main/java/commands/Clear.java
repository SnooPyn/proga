package commands;

import controller.CommandWithoutArg;


public class Clear implements CommandWithoutArg {


    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "clear";
    }
}
