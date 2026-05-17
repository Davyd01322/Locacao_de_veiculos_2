package project.communication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

import javax.xml.crypto.Data;

import project.common.Request;
import project.common.Message;
import project.common.Reply;

public class CommunicationModuleServer extends CommunicationModule{

    CommunicationModuleServer(int port, String host){
        try{
            super.socket = new DatagramSocket(port);
            super.ipAddress = InetAddress.getByName(host);
        } catch (SocketException e){
            System.out.println("CommunicationModule.java: 1");
            System.out.println("Erro ao criar o socket: " + e.getMessage());
        } catch (UnknownHostException e){
            System.out.println("CommunicationModule.java: 1");
            System.out.println("Erro ao criar o ipAddress: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("CommunicationModule.java: 1");
            System.out.println(String.format("A porta %d está fora do range: " + e.getMessage(),port));
        }
    }

    @Override
    public void send(Message obj, InetAddress ip,int port) throws Exception{
        try{
            // Aqui estamos fazendo a serialização do objeto
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            byte[] data = bos.toByteArray();

            // Aqui estamos enviando o pacote com o objeto serializado
            DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
            this.socket.send(packet);
        } catch (InvalidClassException e){
            System.out.println("CommunicationModuleServer.java: 1");
            System.out.println(e.getMessage());
        } catch (NotSerializableException e){
            System.out.println("CommunicationModuleServer.java: 1");
            System.out.println("NotSerializableException: " + e.getMessage());
        } catch(IOException e){
            System.out.println("CommunicationModuleServer.java: 1");
            System.out.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public Request receive() throws Exception{
        Request msg = new Request();
        try{
            // Aqui vamos receber os dados empacotadas
            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(packet); //
             
            // Processo de desserialização
            ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            ObjectInputStream in = new ObjectInputStream(bin); //
            msg = (Request) in.readObject();
        } catch (IllegalArgumentException e){
            System.out.println("CommunicationModuleServer.java: 2");
            System.out.println("IllegalArgumentException: " + e.getStackTrace());
        } catch (IOException e){
            System.out.println("CommunicationModuleServer.java: 2");
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("CommunicationModule.java: 2");
            System.out.println("ClassNotFoundException: " + e.getStackTrace());
        }
        return msg;
    }

    public InetAddress getLocalIP(){
        return this.ipAddress;
    }

    public void closeSocket(){
        this.socket.close();
    }
}