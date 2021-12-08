package connection;

import utilites.Deserializator;
import shells.TicketShell;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;


public class ServerReceiver {
    private Deserializator deserializator = new Deserializator();
    private ByteBuffer buffer = ByteBuffer.allocate(1000000);
    private DatagramChannel channel;
    private InetSocketAddress address;
    private HashMap<String, TicketShell> receivedCities = new HashMap<>();
    private int serverPort;

    public ServerReceiver(int serverPort) {
        this.serverPort = serverPort;
        this.address = new InetSocketAddress("localhost", serverPort);
        try {
            this.channel = DatagramChannel.open();
            this.channel.configureBlocking(false);
            this.channel.bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        while (true) {
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                Object object = deserializator.toDeserialize(bytes);
                buffer.clear();
                if (object instanceof TicketShell) {
                    receivedCities.put(((TicketShell) object).getLogin(), (TicketShell) object);
                    return receiveObject();
                } else return object;
            }
        }
    }

    public TicketShell getReceivedCityShellForUser(String login, String commandname) {
        TicketShell cityShell = receivedCities.get(login);
        if (cityShell != null) {
            if (cityShell.getCommandName().equals(commandname)) {
                receivedCities.remove(login);
                return cityShell;
            } else return null;
        } else return null;
    }
}
