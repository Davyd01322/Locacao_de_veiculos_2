package project.servidor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import project.common.Caminhao;
import project.common.CarroDePasseio;
import project.common.MeiosDeTransporte;
import project.common.Moto;
import project.common.Onibus;
import project.common.Reply;
import project.common.Request;

public class Dispatcher{
    private LocadoraDeVeiculos locadora;
    private int idMessage;

    public Dispatcher(){
        try{
            this.locadora = new LocadoraDeVeiculos();
            ArrayList<MeiosDeTransporte> transportes = new ArrayList<>();

            transportes.add(new Moto("Honda", "cgFan160", "Vermelha", "BL4CK"));
            transportes.add(new Moto("Honda", "NRX160", "Preta", "D4RT2"));
            transportes.add(new Moto("Yamaha", "xj6", "Azul", "M4D4R4"));
            transportes.add(new CarroDePasseio("Subaru", "ImprezaWRX", "Azul", "L1GTH"));
            transportes.add(new CarroDePasseio("Wolkswagen", "Golf", "Branco", "J0H4N"));
            transportes.add(new CarroDePasseio("Honda", "Civic", "Prata", "M4R1K"));
            transportes.add(new CarroDePasseio("Chevrolet", "Camaro", "Amarelo", "ER3N"));
            transportes.add(new Onibus("Wolskwagem", "Scolarship", "Amarelo", "5CH00L"));
            transportes.add(new Caminhao("Mercedes", "Axor", "Prata", "154BEL4"));
            transportes.add(new Caminhao("Ford", "Torqshift", "Prata", "D4K1"));
                
            for(int i = 0; i < transportes.size(); i++){
                locadora.novoVeiculo(transportes.get(i));            
            }
            this.idMessage = 0;
        } catch(RemoteException e){
            System.out.println("Dispatcher.java: 1");
            System.out.println("RemoteException: " + e.getMessage());
        }
    }

    public Reply doOperation(Request rqst){
        this.idMessage += 1;
        Reply rpl = new Reply();
        switch(rqst.getMethodID()){
            case 1:
                try{
                    ByteArrayInputStream dataIn = new ByteArrayInputStream(rqst.getArguments());
                    ObjectInputStream objIn = new ObjectInputStream(dataIn);
                    MeiosDeTransporte veiculo = (MeiosDeTransporte) objIn.readObject();
                    this.locadora.novoVeiculo(veiculo);
                    rpl = new Reply(0, this.idMessage, "Novo veiculo adicionado");
                } catch(IOException e){
                    System.out.println("IOException: " + e.getStackTrace());
                } catch(ClassNotFoundException e){
                    System.out.println("ClassNotFoundException: " + e.getStackTrace());
                }
                break;
            case 2:
                try{
                    ByteArrayInputStream dataIn = new ByteArrayInputStream(rqst.getArguments());
                    byte[] buffer = dataIn.readAllBytes();
                    String argument = new String(buffer);
                    MeiosDeTransporte v = this.locadora.Alugar(argument);
                    rpl = new Reply(0, this.idMessage, v);
                } catch(RemoteException e){
                    System.out.println("RemoteException: " + e.getStackTrace());
                }
                break;
            case 3:
                try{
                    ByteArrayInputStream dataIn = new ByteArrayInputStream(rqst.getArguments());
                    ObjectInputStream objIn = new ObjectInputStream(dataIn);
                    MeiosDeTransporte veiculo = (MeiosDeTransporte) objIn.readObject();
                    this.locadora.Devolver(veiculo);
                    rpl = new Reply(0, this.idMessage, "Veiculo Devolvido");
                } catch(RemoteException e){
                    System.out.println("RemoteException: " + e.getStackTrace());
                } catch(IOException e){
                    System.out.println("IOException: " + e.getStackTrace());
                } catch(ClassNotFoundException e){
                    System.out.println("ClassNotFoundException: " + e.getStackTrace());
                }
                break;
            case 4:
                try{
                    ByteArrayInputStream dataIn = new ByteArrayInputStream(rqst.getArguments());
                    ObjectInputStream objIn = new ObjectInputStream(dataIn);
                    MeiosDeTransporte veiculo = (MeiosDeTransporte) objIn.readObject();
                    this.locadora.venderVeiculo(veiculo);
                    rpl = new Reply(0, this.idMessage, "Veiculo vendido");
                } catch(RemoteException e){
                    System.out.println("RemoteException: " + e.getStackTrace());
                } catch(IOException e){
                    System.out.println("IOException: " + e.getStackTrace());
                } catch(ClassNotFoundException e){
                    System.out.println("ClassNotFoundException: " + e.getStackTrace());
                }
                break;
            case 5:
                try{
                    String text = this.locadora.listarDisponiveis();
                    rpl = new Reply(0, this.idMessage, text);
                } catch(RemoteException e){
                    System.out.println("RemoteException: " + e.getStackTrace());
                }
                break;
            default:
                break;
        }

        return rpl;
    }
}