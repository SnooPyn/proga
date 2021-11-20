package connection;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class ClientReceiver {
    private static DatagramSocket clientSocket = null;
    private byte[] data = new byte[100000];

    public ClientReceiver(int clientPort) throws SocketException {
        clientSocket = new DatagramSocket(clientPort);
    }

    public String receive() throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        clientSocket.setSoTimeout(2000);
        clientSocket.receive(packet);
        clientSocket.setSoTimeout(0);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }


}