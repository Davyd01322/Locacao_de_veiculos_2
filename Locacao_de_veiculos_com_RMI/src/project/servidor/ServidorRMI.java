package project.servidor;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import org.xml.sax.SAXException;

public class ServidorRMI{
    public static void main(String[] args){
        Skeleton sklt = new Skeleton();
        try{
            sklt.listen();
        } catch(Exception e){
            System.out.println("ServidorRMI.java: 1");
            System.out.println("Exception: " + e.getMessage());
        }
    }
}