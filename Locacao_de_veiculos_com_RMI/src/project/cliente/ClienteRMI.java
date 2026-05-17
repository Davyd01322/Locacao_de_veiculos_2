package project.cliente;

import java.net.MalformedURLException;
import java.rmi.*;

import project.common.*;

public class ClienteRMI{
    public static void main(String[] args){
        // Criar o proxy no lado cliente para tornar as chamadas remotas como chamadas locais
        LocadoraDeVeiculosProxy locadora = new LocadoraDeVeiculosProxy();
        try{
            // Primeiro o Cliente irá chamar a função listarDisponiveis
            System.out.println(locadora.listarDisponiveis());

            // Depois ele irá alugar o segundo veiculo e mostrá-lo na tela, neste caso é:
            /*
                2. =====================
                Tipo: Carro de passeio
                Placa:J0H4N
                Modelo:Golf
                Marca:Wolkswagen
                Cor:Branco
                ========================
            */

            MeiosDeTransporte v = locadora.Alugar("2");
            System.out.println(v.toString());

            // novamente mostrará a lista de veículos disponíveis, confirmando que o veiculo
            // foi alugado, uma vez que ele não estará disponível para o alugel
            System.out.println(locadora.listarDisponiveis());

            // Por fim o veiculo alugado será devolvido
            locadora.Devolver(v);

            System.out.println(locadora.listarDisponiveis());
            System.out.println(locadora.listarDisponiveis());
            locadora.closeProxy();
        } catch(RemoteException e){
            System.out.println("ClienteRMI.java");
            System.out.println("RemoteException: " + e.getMessage());
        }
    }
}