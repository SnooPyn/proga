package commands;

import controller.CommandWithoutArg;

public class Info implements CommandWithoutArg {

    @Override
    public String execute(Object object) {
        return null;
    }


    @Override
    public String getName() {
        return "info";
    }
}
