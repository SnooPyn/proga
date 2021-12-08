package connection;

import utilites.Serializator;
import java.net.*;

public class ServerSender {
    private Serializator serializator = new Serializator();
    private static DatagramSocket serverSocket = null;
    private  InetAddress address;
    private int port;

    public ServerSender(int port) {
        try {
            serverSocket = new DatagramSocket(port);
            address = InetAddress.getLocalHost();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }


    public void send(Object o) {
        try {
            byte[] sendData = serializator.toSerialize(o);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Client aborted the connection");
        }
    }
}