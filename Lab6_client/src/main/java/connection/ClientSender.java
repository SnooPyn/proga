package connection;


import Utilites.Serializator;
import controller.CommandWithObject;
import controller.Commands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

public class ClientSender {
    boolean isCommandWithObject;
    DatagramChannel channel;
    InetSocketAddress serverAdress;
    ByteBuffer buffer = ByteBuffer.allocate(10000);
    private int clientPort;

    public ClientSender(int serverPort, int clientPort) throws IOException {
        channel = DatagramChannel.open();
        channel.bind(null);
        serverAdress = new InetSocketAddress("localhost", serverPort);
        channel.configureBlocking(false);
        this.clientPort = clientPort;
    }

    public boolean isCommandWithObject() {
        return isCommandWithObject;
    }

    public void sendClientPort() throws IOException {
        buffer.clear();
        buffer.put(String.valueOf(clientPort).getBytes());
        buffer.flip();
        channel.send(buffer, serverAdress);
        buffer.flip();
    }
    public void send(String text) throws IOException {
        buffer.clear();
        buffer.put(text.getBytes());
        buffer.flip();
        channel.send(buffer, serverAdress);
        buffer.flip();
    }

    public void send(Object object) throws IOException {
        buffer.clear();
        buffer.put(Serializator.toSerialize(object));
        buffer.flip();
        channel.send(buffer, serverAdress);
        buffer.flip();
    }

    public void sendCommand(Commands command) throws IOException, ClassNotFoundException {
        ArrayList commandAndArgument = this.toPackCommand(command);
        this.sendClientPort();
        buffer.clear();
        //thisCommand += invoker.getCommand().getName()+" "+ invoker.getArg() +"\n";
        buffer.put(Serializator.toSerialize(commandAndArgument));
        buffer.flip();
        channel.send(buffer, serverAdress);
        buffer.flip();
        try {
            CommandWithObject commandWithObject = (CommandWithObject) command.getCommand();
            isCommandWithObject = true;
        } catch (Exception e) {
            isCommandWithObject = false;
        }
    }


    public ArrayList toPackCommand(Commands command) {
        ArrayList commandAndArgument = new ArrayList();
        commandAndArgument.add(command.getCommand());
        commandAndArgument.add(command.getArg());
        return commandAndArgument;
    }
}
