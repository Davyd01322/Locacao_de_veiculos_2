package project.communication;

import java.net.DatagramSocket;
import java.net.InetAddress;

import project.common.Message;
import project.common.Reply;

public abstract class CommunicationModule{
    protected DatagramSocket socket;
    protected InetAddress ipAddress;

    public void send(Message obj, InetAddress ip,int port) throws Exception{};
    public Message receive() throws Exception{
        return new Reply();
    };
}