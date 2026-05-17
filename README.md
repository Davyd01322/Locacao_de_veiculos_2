# Aplicação De locação de veículos usando RMI

## Como executar?
  Basta inicializar o programa ClienteRMI.java (localizado na pasta cliente) na máquina do cliente e o programa ServidorRMI.java (localizado na 
pasta servidor) na máquina do servidor.

## ClienteRMI.java
  Esse é o lado cliente da aplicação e nele é gerenciado a classe chamada LocadoraDeVeiculosProxy que básicamente cria uma abstração que torna
os métodos remotos transparentes para o cliente fazendo as chamadas de método parecerem locais.

## ServidorRMI.java
  Esse é o lado servidor da aplicação e nele é gerenciado uma classe chamada Skeleton que tem 3 funções: receber, chamar o despachante (que no caso
é implementado pela classe Dispatcher) e retornar uma resposta para o cliente, em outras palavras o que skeleton faz é: getRequest(), doOperation()
(da classe Dispatcher) e sendReply.

## Request.java e Reply.java
  Essas classes servem para serem serializadas e elas são construídas de modo a carregarem informações importantes dos pacotes. Essas classes são subclasses
da classe abstrata Messege (precisei fazer isso para poder resolver problemas de cast).

## CommunicationModule.java
  É uma classe abstrata que tem duas classes filhas: CommunicationModuleClient.java e CommunicationModuleServer.java. Essas classes implementam a camada de
comunicação de ambos os lados, e para fazer isso são usados datagramas UDP que permitem a comunicação sem estabelecer a comunicação.

## Finalização
  A aplicação simula as três operações cliente: listarDisponiveis() (vai mostrar todos os veiculos disponíveis para aluguel), alugar() (nesse exemplo o 
veiculo de indice 2 será alugado) e devolver() (que devolve o veiculo alugado).
