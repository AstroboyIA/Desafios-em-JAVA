package main.desafio05;

import java.util.Scanner;

import main.desafio05.model.Cliente;
import main.desafio05.model.Pedido;
import main.desafio05.service.PedidoService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PedidoService service = new PedidoService();

        System.out.println("Bem vindo ao sistema de gerenciamento de pedidos da Lanchonete!");

        Cliente cliente = service.cadastrarCliente(sc);

        Pedido pedido = service.gerenciarPedido(sc, cliente);

        service.exibirResumo(cliente, pedido);

        sc.close();

    }
}