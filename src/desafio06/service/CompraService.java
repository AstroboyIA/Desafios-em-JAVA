package desafio06.service;

import java.util.Scanner;

import desafio06.model.Carrinho;
import desafio06.model.Cliente;
import desafio06.model.Produto;

public class CompraService {

    public Cliente cadastrarCliente(Scanner sc) {
        Cliente cliente = null;
        String nomeCliente;

        System.out.println("Para continuar cadaste um cliente!");
        System.out.println("Informe o nome do cliente:");
        nomeCliente = sc.nextLine();

        return cliente;
    }

    public Produto cadastraProduto(Scanner sc) {

        String nomeProduto;
        Double precoProduto;
        int opcao;

        System.out.println("Para continuar cadastre um produto!");
        System.out.println("");

        do {
            System.out.println("Qual o nome do produto?:");
            nomeProduto = sc.nextLine();
            System.out.println("");
            System.out.println("Qual o preço do produto?:");
            precoProduto = sc.nextDouble();
            sc.nextLine();
            System.out.println("");

            System.out.println("Deseja cadastrar outro produto?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextInt();
            sc.nextLine();
        } while (opcao == 1);

        Produto produto = new Produto(nomeProduto, precoProduto);

        return produto;
    }

    public void exibirResumo(Cliente cliente, Carrinho carrinho){

        double total = 0;
        for (Produto p : carrinho.getProdutos()){
            total =+ p.getPreco();
        }

        System.out.println("Finalizando compras...");
        System.out.println("Aqui está o resumo da sua compra:");
        System.err.println("A compra do Cliente " + cliente.getNome());
        System.out.println("Ficou no valor de: " + total );
        if (total > 100) {
            System.out.println("Compra de valor alto!");
        }else{
            System.out.println("Compra comum!");
        }
    }
}
