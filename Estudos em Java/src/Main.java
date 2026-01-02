import java.util.Scanner;

public class Main {
    public static  void main (String[] args) {

        //criação do scanner para inserir dados

        Scanner entrada = new Scanner(System.in);

        //criando POO

        ContaBancaria minhaConta = new ContaBancaria();

        //saldação de boas vindas para deixar a aplicação amigavel

        System.out.println("--- BEM VINDO AO BANCO ---");

        // Função criar conta //
        System.out.println("Vamos criar sua conta?");
        System.out.println("Qual é o nome do titular da conta: ");
        minhaConta.titular = entrada.nextLine();
        minhaConta.numeroConta = 12345;
        minhaConta.saldo = 0.0;
        //System.out.println("numeroConta criada com sucesso!");
        //System.out.println("A conta de " + minhaConta.titular + " recebeu a númeração: " + minhaConta.numeroConta + " e tem saldo de: " + minhaConta.saldo);

        minhaConta.exibirDados();

        // Função depositar //

        System.out.println("Quanto você quer depositar na sua conta ?:");
        double valorDeposito = entrada.nextDouble();
        minhaConta.depositar(valorDeposito);
        minhaConta.exibirDados();

        // Função sacar //

        System.out.println("Quanto você quer sacar da sua conta ?:");
        double valorSaque = entrada.nextDouble();
        minhaConta.sacar(valorSaque);

        //mensagem de finalização

        minhaConta.exibirDados();
        System.out.println("---Fim da Operação---");


        entrada.close();
    }
}