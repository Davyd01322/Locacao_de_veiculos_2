package project.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LocadoraDeVeiculosInterface extends Remote{
	void novoVeiculo(MeiosDeTransporte v) throws RemoteException;
	MeiosDeTransporte Alugar(String s) throws RemoteException;
	void Devolver(MeiosDeTransporte v) throws RemoteException;
	void venderVeiculo(MeiosDeTransporte v) throws RemoteException;
	String listarDisponiveis() throws RemoteException;
}
