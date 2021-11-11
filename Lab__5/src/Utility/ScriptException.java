package Utility;

public class ScriptException extends Exception {
    private String wrong = "";

    public ScriptException(String wrong) {
        this.wrong = wrong;
    }

    public  String getWrong() {
        return wrong;
    }
}