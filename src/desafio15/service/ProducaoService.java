package desafio15.service;

import java.util.Scanner;

import desafio15.model.EtapaProducao;
import desafio15.model.PedidoProducao;

public class ProducaoService {
    

    public void BoasVindas() {
        System.out.println("Boas Vindas ao sistema de produção de pedidos!");
    }

    public PedidoProducao acidionarPedido (Scanner sc) {

        String nomeCliente;
        int tempoMetaMinutos = 0;

        System.out.println("Cadastro de pedidos iniciado.");
        System.out.println("Cadastre o pedido e nome do cliente: ");
        System.out.println("Exemplo de cadastro : Bolo de festa - Cliente Maria");
        nomeCliente = sc.nextLine();
        System.out.println("Qual a meta (em minutos) de conclusão do pedido?");
        tempoMetaMinutos = sc.nextInt();
        sc.nextLine();
        PedidoProducao pedido = new PedidoProducao(nomeCliente, tempoMetaMinutos);
        System.out.println("Pedido cadastrado com sucesso!");

        return pedido;
    }

    public void adicionarEtapas (Scanner sc, PedidoProducao pedido) {

        String nomeEtapa;
        int tempoEstimadoMinutos;
        int tempoExecutadoMinutos;
        byte escolha;

        System.out.println("Agora, cadastre as etapas de preparação para o pedido.");

        do {
            System.out.println("\nQual etapa você deseja cadastrar?");
            nomeEtapa = sc.nextLine();
            System.out.println("\nQual a estimativa de conclusão dessa etapa?");
            tempoEstimadoMinutos = sc.nextInt();
            sc.nextLine();
            System.out.println("\nQual foi o tempo de execução nessa etapa?");
            tempoExecutadoMinutos = sc.nextInt();
            sc.nextLine();
            EtapaProducao etapa = new EtapaProducao(nomeEtapa, tempoEstimadoMinutos, tempoExecutadoMinutos);
            pedido.adicionarEtapa(etapa);
            System.out.println("Etapa cadastrada com sucesso!");

            System.out.println("\nVocê deseja adicionar outra etapa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            escolha = sc.nextByte();
            sc.nextLine();
        }while (escolha == 1);
    }

    public void resumoFinal (PedidoProducao pedido) {
        System.out.println("Resumo final do pedido:");
        System.out.println("Pedido: "+ pedido.getNomeCliente());
        System.out.println("Status: "+ pedido.calcularStatus().getStatus());
        System.out.println("Progresso: "+ pedido.calcularProgressoExecucao()+"%");
        System.out.println("Previsão total: "+ pedido.calcularPrevisaoTotalMinutos()+" minutos");
        System.out.println("Meta planejada: "+ pedido.getTempoMetaMinutos()+" minutos");
        System.out.println("Situação de prazo: "+ pedido.calcularSituacaoPrazo().getSituacaoPrazo());
        System.out.println("Risco operacional: "+ pedido.calcularRisco().getRisco());
        System.out.println("Índice de cumprimento da meta: " + pedido.calcularIndiceCumprimentoMeta()+"%");
    }
}
