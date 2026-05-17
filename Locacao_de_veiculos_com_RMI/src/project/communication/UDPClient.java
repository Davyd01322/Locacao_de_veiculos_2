package project.communication;

import java.net.*;

import project.common.Message;
import project.common.Reply;
import project.common.Request;

public class UDPClient{
    private CommunicationModuleClient cm;
    private int CurrentID;

    public UDPClient(){
        this.cm = new CommunicationModuleClient(7896, "10.11.111.30");
        this.CurrentID = 0;
    }

    public void send(Request r){
        try{
            // Pega o endereço do servidor
            InetAddress ipDestination = InetAddress.getByName("10.11.111.30");
            // envia o objeto request com os argumentos serializados
            this.cm.send(r, ipDestination,4321);
        } catch(Exception e){
            System.out.println("UDPCliente.java:1");
            System.out.println("Exception e: " + e.getMessage());
        }
    }

    public Reply receive(){
        Reply rpl = new Reply();
        try{
            rpl = this.cm.receive();
        } catch(Exception e){
            System.out.println("UDPCliente.java:2");
            System.out.println("Exception e: " + e.getMessage());
        }
        return rpl;
    }

    public int GetCurrentID(){
        return this.CurrentID;
    }

    public void closeClient(){
        this.cm.closeSocket();
    }
}