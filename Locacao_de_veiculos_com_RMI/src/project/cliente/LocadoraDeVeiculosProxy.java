package project.cliente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

import project.common.LocadoraDeVeiculosInterface;
import project.common.MeiosDeTransporte;
import project.communication.UDPClient;
import project.common.Reply;
import project.common.Request;

// O proxy cria uma abstração que vai tornar os métodos da classe remota, transparentes para
// o cliente.
public class LocadoraDeVeiculosProxy implements LocadoraDeVeiculosInterface{
	private UDPClient client;
	private Request rqst;
	private Reply rpl;
	private int ID;

	LocadoraDeVeiculosProxy(){
		// O UDPCliente é uma classe que abstrai para o proxy as questões de sockets
		// criando desacoplamento, modularizando e especializando cada classe
		this.client = new UDPClient();
		this.ID = 0;
	}

    public void novoVeiculo(MeiosDeTransporte v) throws RemoteException{
		try{
			// Serialização do objeto
			ByteArrayOutputStream dataOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(dataOut);
			objOut.writeObject(v);
			byte[] buffer = new byte[4096];
			buffer = dataOut.toByteArray();

			// envio do pacote com o objeto serializado
        	this.rqst = new Request(1, this.ID, "Locadora", 1, buffer);
			this.client.send(this.rqst);
		} catch (IOException e){
			System.out.println("IOException: " + e.getStackTrace());
		}
    }

	public MeiosDeTransporte Alugar(String s) throws RemoteException{
		try{
			// Enviar a requisição
			byte[] buffer = s.getBytes();
			this.rqst = new Request(1, this.ID, "Locadora", 2, buffer);
			this.client.send(this.rqst);

			// Receber a resposta
			this.rpl = this.client.receive();
			
			// Desserializar a resposta
		} catch (ClassCastException e){
			System.out.println("LocadoraDeVeiculosProxy.java: 1");
			System.out.println("ClassCastException: " + e.getMessage());
		}
		return (MeiosDeTransporte) this.rpl.GetResult();
	}

	public void Devolver(MeiosDeTransporte v) throws RemoteException{
		try {
			ByteArrayOutputStream dataOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(dataOut);
			
			objOut.writeObject(v);
			byte[] buffer = new byte[4096];
			buffer = dataOut.toByteArray();

			this.rqst = new Request(1, this.ID, "Locadora", 3, buffer);
			this.client.send(this.rqst);
		} catch(IOException e){
			System.out.println("IOException: " + e.getStackTrace());
		}
	}

	public void venderVeiculo(MeiosDeTransporte v) throws RemoteException{
		try{
			ByteArrayOutputStream dataOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(dataOut);
			objOut.writeObject(v);

			byte[] buffer = new byte[4096];
			buffer = dataOut.toByteArray();
			this.rqst = new Request(1, this.ID, "Locadora", 4, buffer);
			this.client.send(this.rqst);
		} catch(IOException e){
			System.out.println("IOException: " + e.getStackTrace());
		}
	}

	public String listarDisponiveis() throws RemoteException{
		// Serializar os argumentos
		//byte[] buffer = new byte[4096];
		this.rqst = new Request(1, this.ID, "Locadora", 5, null);
		this.client.send(this.rqst);

		this.rpl = (Reply) this.client.receive();
		return (String) this.rpl.GetResult();
	}

	public void closeProxy(){
		this.client.closeClient();
	}
}