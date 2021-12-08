package connection;

import controller.Collection;
import dataBase.UsersDataBase;
import shells.RequestShell;
import shells.ResponseShell;
import state.ResponseStatus;


class AuthHandler implements Runnable {
    private ServerSender sender;
    private RequestShell authRequest;
    private UsersDataBase usersDataBase;

    public AuthHandler(ServerSender sender, RequestShell authRequest, UsersDataBase usersDataBase) {
        this.sender = sender;
        this.authRequest = authRequest;
        this.usersDataBase = usersDataBase;
    }

    @Override
    public void run() {
        try {
            sender.setPort(authRequest.getPort());
            String login = authRequest.getLogin();
            String password = authRequest.getPassword();
            ResponseShell authResponse;
            if (authRequest.getMode() == 1)
                if (!(usersDataBase.isValue("login", login))) {
                    usersDataBase.addUser(login, password);
                    authResponse = new ResponseShell(ResponseStatus.VALID, "User passed the authorization", Collection.getTickets());
                    authResponse.setCreationDate(Collection.getCreationDate());
                    sender.send(authResponse);
                } else
                    sender.send(new ResponseShell(ResponseStatus.NON_VALID, "The login is busy", null));
            else {
                if (usersDataBase.isValue("login", login)) {
                    if (usersDataBase.isValue("password", password)) {
                        authResponse = new ResponseShell(ResponseStatus.VALID, "User passed the authentication", Collection.getTickets());
                        authResponse.setCreationDate(Collection.getCreationDate());
                        sender.send(authResponse);
                    } else
                        sender.send(new ResponseShell(ResponseStatus.NON_VALID, "Incorrect login or password", null));
                } else
                    sender.send(new ResponseShell(ResponseStatus.NON_VALID, "Incorrect login or password", null));
            }
            System.out.println("User [" + authRequest.getLogin() + "] connected to the server.\n");

        } catch (Exception e) {
        }
    }
}
