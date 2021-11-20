package Commands;


import controller.CommandWithoutArg;


public class Help implements CommandWithoutArg {

    @Override
    public String execute(Object object){
        return null;
    }

    @Override
    public String getName() {
        return "help";
    }
}
