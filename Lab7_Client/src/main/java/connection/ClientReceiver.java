package connection;

import utilites.Deserializator;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class ClientReceiver {
    private DatagramSocket clientSocket;

    public ClientReceiver(int clientPort) throws SocketException {
        this.clientSocket = new DatagramSocket(clientPort);
    }


    public Object receiveObject() {
        try {
            byte[] data = new byte[1000000];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            clientSocket.setSoTimeout(2000);
            clientSocket.receive(packet);
            clientSocket.setSoTimeout(0);
            return Deserializator.toDeserialize(packet.getData());
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
}


