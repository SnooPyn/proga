package Commands;

import controller.CommandWithoutArg;

public class Head implements CommandWithoutArg {

    @Override
    public String execute(Object object) {
        return null;
    }


    @Override
    public String getName() {
        return "head";
    }
}
