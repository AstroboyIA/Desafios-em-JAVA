package desafio05;

import java.util.Scanner;

import desafio05.model.Cliente;
import desafio05.model.Produto;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao sistema de gerenciamento de pedidos da Lanchonete");

        System.out.println("Digite o nome do cliente:");
        String infoNome = sc.nextLine();
        Cliente cliente = new Cliente(infoNome);

        if (infoNome == null) {
            System.out.println("Nenhum nome inserido, por favor, tente novamente");
        }

        System.out.println("Qual o produto do cliente?");
        String infoProduto = sc.nextLine();

        System.out.println("Qual o valor do produto ?");
        Double infoValor = sc.nextDouble();
        sc.nextInt();

        Produto produto = new Produto(infoProduto, infoValor);
        
    }
}
