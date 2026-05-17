package project.communication;

import java.net.InetAddress;

import project.common.Message;
import project.common.Reply;
import project.common.Request;

public class UDPServer{
    private CommunicationModuleServer cm;

    public UDPServer(){
        this.cm = new CommunicationModuleServer(4321, "10.11.111.30");
    }

    public void send(Message r){
        try{
            InetAddress ipDestination = InetAddress.getByName("10.11.111.30");
            this.cm.send(r, ipDestination,7896);
        } catch(Exception e){
            System.out.println("UDPServer.java: 1");
            System.out.println("'Exception: " + e.getStackTrace());
        }
    }

    public Request receive(){
        Request rqst = new Request();
        try{
            rqst = this.cm.receive();
        } catch(Exception e){
            System.out.println("UDPServer.java: 2");
            System.out.println("Exception: " + e.getMessage());
        }
        return rqst;
    }
}