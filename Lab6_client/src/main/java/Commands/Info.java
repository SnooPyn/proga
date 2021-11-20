package Commands;


import controller.Collection;
import controller.CommandWithoutArg;

public class Info implements CommandWithoutArg {

    @Override
    public String execute(Object object) {
        return Collection.getInfo();
    }


    @Override
    public String getName() {
        return "info";
    }
}