package connection;

import controller.Collection;
import dataBase.TicketDataBase;
import dataBase.UsersDataBase;
import shells.RequestShell;
import shells.ResponseShell;
import state.ResponseStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;


    public class App {
        private ServerReceiver receiver;
        private ServerSender sender;
        private DataBaseController dataBaseController;
        private UsersDataBase dbUsers;
        private TicketDataBase dbTickets;

        public App(ServerSender sender, ServerReceiver receiver, String url, String user, String password) throws SQLException {
            this.receiver = receiver;
            this.sender = sender;
            this.dataBaseController = new DataBaseController(url, user, password);
            this.dbUsers = dataBaseController.getUserDataBase();
            this.dbTickets = dataBaseController.getDataBase();
            System.out.println("Server is running on " + receiver.getServerPort() + " port.");
        }

        public void start() throws IOException {
            Collection.setTickets(dbTickets.getCollection());
            System.out.println(this.getCollectionInfo());
            this.checkForSaveCommand();
            while (true)
                this.toHandleRequest();
        }

        private String getCollectionInfo() {
            if ((Collection.getSize() == 0))
                return ("Collection is empty");
            else
                return ("Collection is filled by " + Collection.getSize() + " elements.");
        }


        /**
     * Request Handler
     * Verification request for authentication/authorization or execution of a command with verification of correctness
     * login password
     */
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    private void toHandleRequest() throws IOException {
        try {
            RequestShell request = (RequestShell) receiver.receiveObject();
            //if the command is not initialized in the request, then it is an authorization/authentication request
            if (request.getCommand() == null)
                //We call the authorization/authentication handler stream through the executors service
                forkJoinPool.execute(new AuthHandler(sender, request, dbUsers));
            else {
                /**
                 * validation on the server, each command processing request contains a login
                 * and the password that we check with the database every time
                 */
                if (dbUsers.checkLoginAndPassword(
                        request.getLogin(),
                        request.getPassword())) {
                    //calling the command processing THREAD using
                    CommandHandler user = new CommandHandler(request, receiver, sender);
                    user.start();
                    Thread.sleep(20);
                } else {
                    sender.send(new ResponseShell(ResponseStatus.UNSUCCES, "Hacking attempt. Login and password have not been validated on the server", null));
                }
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {

        }
    }

    /**
     * User authentication or authorization depending on the StatusMode
     * Checking the password, login in the database
     **/


    public void checkForSaveCommand() {
        Thread backgroundReaderThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.interrupted()) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.equalsIgnoreCase("save")) {
                        dbTickets.loadCollection(Collection.getTickets());
                    }
                    if (line.equalsIgnoreCase("exit")) {
                        dbTickets.loadCollection(Collection.getTickets());
                        System.exit(0);
                    }
                }
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}