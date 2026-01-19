package desafio06.service;

import java.util.Scanner;

import desafio06.model.Cliente;
import desafio06.model.Produto;

public class CompraService {
    

    public Cliente cadastrarCliente(Scanner sc){
        Cliente cliente = null;
        String nomeCliente;
        

        System.out.println("Para continuar cadaste um cliente!");
        System.out.println("Informe o nome do cliente:");
        nomeCliente = sc.nextLine();
        
        return cliente;
    }
    public Produto cadastraProduto(Scanner sc){
        
        System.out.println("Para continuar cadastre um produto!");

        System.out.println("Qual o nome do produto?:");
        String nomeProduto = sc.nextLine();
        System.out.println("Qual o preço do produto?:");
        Double precoProduto = sc.nextDouble();
        sc.nextLine();
        
        Produto produto = new Produto(nomeProduto, precoProduto);
        
        return produto;
    }
}
