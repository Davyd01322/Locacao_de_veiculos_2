package project.common;

public class Reply extends Message{
    private Object result;
    
    public Reply(){}

    public Reply(int msg, int id, Object resultObject){
        super.messageType = msg;
        super.ID = id;
        this.result = resultObject;
    }

    public Object GetResult(){
        return this.result;
    }

    public int GetReplyID(){
        return super.ID;
    }

    public int GetMessageType(){
        return this.messageType;
    }
}