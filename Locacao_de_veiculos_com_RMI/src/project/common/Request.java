package project.common;

public class Request extends Message{
    private int messageType; // 0:Reply, 1:Request
    private int ID;
    private String remoteObjectRef;
    private int methodID;
    private byte[] arguments;

    public Request(){};
    
    public Request(int messageType, int requestID, String remoteObjectRef, int methodID, byte[] args){
        super.messageType = messageType;
        super.ID = requestID;
        this.remoteObjectRef = remoteObjectRef;
        this.methodID = methodID;
        this.arguments = args;
    }

    public int GetMessageType(){
        return this.messageType;
    }

    public int GetRequestID(){
        return this.ID;
    }

    public String getRemoteObjectRef(){
        return this.remoteObjectRef;
    }

    public int getMethodID(){
        return this.methodID;
    }

    public byte[] getArguments(){
        return this.arguments;
    }
}