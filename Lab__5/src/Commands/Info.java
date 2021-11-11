package Commands;


import Controller.Collection;
import Controller.CommandWithoutArg;

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