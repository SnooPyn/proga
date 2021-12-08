package shells;

import controller.Commandable;

import java.io.Serializable;

public class RequestShell implements Serializable {
    private int port;
    private String login;
    private String password;
    private Commandable command;
    private String argument;
    private Integer mode;

    public RequestShell(int port, String login, String password, Commandable command, String argument) {
        this.port = port;
        this.login = login;
        this.password = password;
        this.command = command;
        this.argument = argument;
    }

    public RequestShell(int port, String login, String password) {
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Commandable getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }


    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCommand(Commandable command) {
        this.command = command;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "port: " + port + "\n" +
                "login: " + login + "\n" +
                "password: " + password + "\n" +
                "command: " + command + "\n" +
                "argument: " + argument + "\n";
    }

}