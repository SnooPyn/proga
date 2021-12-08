package connection;


import utilites.Serializator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSender {
    private int clientPort;
    private int serverPort;
    private Serializator serializator = new Serializator();
    private DatagramChannel channel;
    private InetSocketAddress serverAdress;
    private ByteBuffer buffer = ByteBuffer.allocate(1000000);


    public ClientSender(int serverPort, int clientPort) throws IOException {
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.channel = DatagramChannel.open();
        this.channel.bind(null);
        this.serverAdress = new InetSocketAddress("localhost", serverPort);
        this.channel.configureBlocking(false);

    }

    public int getClientPort() {
        return clientPort;
    }


    public void send(Object object) throws IOException {
        buffer.clear();
        byte[] serializedObject = serializator.toSerialize(object);
        buffer.put(serializedObject);
        buffer.flip();
        channel.send(buffer, serverAdress);
        buffer.flip();
    }

}
