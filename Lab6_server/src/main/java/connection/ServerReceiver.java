package connection;


import Utilites.Deserializator;
import ticket.Ticket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;


public class ServerReceiver {
    private ByteBuffer buffer = ByteBuffer.allocate(10000);
    private DatagramChannel channel;
    private DatagramSocket socket;
    private InetSocketAddress address;
    private int serverPort;

    public ServerReceiver(int serverPort) throws IOException {
        this.serverPort = serverPort;
        this.address = new InetSocketAddress("localhost", serverPort);
        this.channel = DatagramChannel.open();
        this.socket = channel.socket();
        this.channel.configureBlocking(false);
        this.channel.bind(address);
    }

    public int getPort() {
        return serverPort;
    }

    public String receive() throws IOException {
        String s = "";
        while (true) {
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                s = new String(bytes);
                buffer.clear();
                return s;
            }
        }
    }

    public Ticket receiveTicket() throws IOException {
        while (true) {
            buffer.clear();
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                try {
                    Object vehicle = Deserializator.toDeserializeWithExc(bytes);
                    buffer.clear();
                    return (Ticket) vehicle;
                } catch (Exception e) {
                    buffer.clear();
                    return receiveTicket();
                }

            }
        }
    }

    public ArrayList receiveCommand() throws IOException {
        while (true) {
            buffer.clear();
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                ArrayList commandArgumentObject = (ArrayList) Deserializator.toDeserialize(bytes);
                buffer.clear();
                return commandArgumentObject;
            }
        }
    }

}