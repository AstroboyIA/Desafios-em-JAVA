package desafio05;

import java.util.Scanner;

import desafio05.model.Cliente;
import desafio05.model.Pedido;
import desafio05.model.Produto;

public class Main {
    public static void main(String[] args) {

        String nomeCliente;
        Cliente cliente = null;
        byte opcao;
        byte continuar;

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao sistema de gerenciamento de pedidos da Lanchonete!");

        do {
            System.out.println("Digite o nome do cliente:");
            nomeCliente = sc.nextLine().trim();

            if (nomeCliente.isEmpty()) {
                System.out.println("Nenhum nome inserido, por favor, tente novamente!");
            } else {
                cliente = new Cliente(nomeCliente);
                System.out.println("Cliente cadastrado com sucesso!");
            }

        } while (nomeCliente.isEmpty()); // Repete o loop até inserir um nome

        Pedido pedido = new Pedido();

        System.out.println("Você deseja adicionar itens ao pedido do cliente agora?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        opcao = sc.nextByte();
        sc.nextLine();

        if (opcao == 1) {
            do {
                System.out.println("Qual o produto do cliente?");
                String infoProduto = sc.nextLine().trim();

                System.out.println("Qual o valor do produto ?");
                Double infoValor = sc.nextDouble();
                sc.nextLine();

                Produto produto = new Produto(infoProduto, infoValor);
                pedido.addProduto(produto);
                System.out.println("Produto adicionado com sucesso.");

                System.out.println("Você deseja adicionar mais um protudo?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                continuar = sc.nextByte();
                sc.nextLine();

            } while (continuar == 1);
            System.out.println("Finalizando pedido!");

        } else {
            System.out.println("Finalizando pedido!");
        }
    }
}