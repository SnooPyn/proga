package connection;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender {

    private boolean isOpen = true;
    private DatagramSocket serverSocket = null;
    private byte[] sendData = new byte[100000];
    private InetAddress address = InetAddress.getLocalHost();
    private int port;

    public ServerSender(int port) throws IOException {
        this.serverSocket = new DatagramSocket(port);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPort(String port) {
        this.port = Integer.parseInt(port);
    }


    public void send(String text) throws IOException {
        if (this.isOpen) {
            sendData = text.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            serverSocket.send(sendPacket);

        }
    }

    public int getPort() {
        return port;
    }
}