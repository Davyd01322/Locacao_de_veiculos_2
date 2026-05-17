package project.servidor;

import project.communication.UDPServer;
import project.common.Reply;
import project.common.Request;

public class Skeleton{
    private UDPServer server;
    private Dispatcher disp;
    private Request rqst;
    private Reply rpl;
    private int ID;

    public Skeleton(){
        this.server = new UDPServer();
        this.disp = new Dispatcher();
        this.ID = 0;
    }

    public void listen() throws Exception{
        while(true){
            try{
                this.rqst = this.getRequest();
                this.rpl = this.disp.doOperation(this.rqst);
                this.sendReply(this.rpl);
            } catch (Exception e){
                System.out.println("Skeleton.java: 1");
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    public Request getRequest() throws Exception{
        this.rqst = new Request();
        try{
            this.rqst = this.server.receive();
        } catch (Exception e){
            System.out.println("Skeleton.java: 2");
            System.out.println("Exception: " + e.getMessage());
        }
        return this.rqst;
    }

    public void sendReply(Reply rpl) throws Exception{
        try{
            this.server.send(rpl);
        } catch (Exception e){
            System.out.println("Skeleton.java: 3");
            System.out.println("Exception: " + e.getMessage());
        }
    }
}