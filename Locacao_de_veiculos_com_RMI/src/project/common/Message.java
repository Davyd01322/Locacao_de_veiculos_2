package project.common;

import java.io.Serializable;

public abstract class Message implements Serializable{
    protected int messageType;
    protected int ID;
}