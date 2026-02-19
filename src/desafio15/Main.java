package main.desafio15;

import java.util.Scanner;

import main.desafio15.model.EtapaProducao;
import main.desafio15.model.PedidoProducao;
import main.desafio15.service.ProducaoService;

public class Main {
    public static void main(String[] args) {
        
        ProducaoService service = new ProducaoService();

        Scanner sc = new Scanner(System.in);

        service.BoasVindas();

        PedidoProducao pedido = service.acidionarPedido(sc);

        service.adicionarEtapas(sc, pedido);

        service.resumoFinal(pedido);

        sc.close();
    }
}