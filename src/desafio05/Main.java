package desafio05;

import java.util.Scanner;

import desafio05.model.Cliente;
import desafio05.model.Produto;

public class Main {
    public static void main(String[] args) {

        String nome;
        Cliente cliente = null;
        byte opcao;

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao sistema de gerenciamento de pedidos da Lanchonete!");

        do {
            System.out.println("Digite o nome do cliente:");
            nome = sc.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Nenhum nome inserido, por favor, tente novamente!");
            } else {
                cliente = new Cliente(nome);
                System.out.println("Cliente cadastrado com sucesso!");
            }

        } while (nome.isEmpty()); // Repete o loop até inserir um nome

        do {
            System.out.println("Você deseja adicionar itens ao pedido do cliente agora?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

            if (opcao == 1) {
                System.out.println("Qual o produto do cliente?");
                String infoProduto = sc.nextLine();

                System.out.println("Qual o valor do produto ?");
                Double infoValor = sc.nextDouble();
                sc.nextLine();

                Produto produto = new Produto(infoProduto, infoValor);
            }else{
                System.out.println("Atendimento finalizado!");
            }

        } while (opcao != 2);
    }
}