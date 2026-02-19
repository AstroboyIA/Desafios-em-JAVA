package main.desafio06;

import java.util.Scanner;

import main.desafio06.model.Carrinho;
import main.desafio06.model.Cliente;
import main.desafio06.service.CompraService;

public class Main {
    public static void main(String[] args) {


        System.out.println("--- Bem vindo ao controle de compras ---");

        Scanner sc = new Scanner(System.in);

        CompraService service = new CompraService();
        
        Carrinho carrinho = new Carrinho();
        
        Cliente cliente = service.cadastrarCliente(sc);

        service.cadastraProduto(sc, carrinho);

        service.exibirResumo(cliente, carrinho);

        carrinho.mensagemFinal();

        sc.close();
    }
}