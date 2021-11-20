package Commands;

import controller.Commandable;

public class CountGreaterThanPrice implements Commandable {

    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "count_greater_than_price";
    }
}