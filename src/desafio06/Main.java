package desafio06;

import java.util.Scanner;

import desafio06.model.Cliente;
import desafio06.model.Produto;
import desafio06.service.CompraService;

public class Main {
    public static void main(String[] args) {


        System.out.println("--- Bem vindo ao controle de compras ---");

        Scanner sc = new Scanner(System.in);

        CompraService service = new CompraService();
        
        Cliente cliente = service.cadastrarCliente(sc);

        Produto produto = service.cadastraProduto(sc);

        
    }
}
