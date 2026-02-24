package desafio16.service;

import java.util.Scanner;

import desafio16.model.AtividadeAtendimento;
import desafio16.model.ChamadoSuporte;

public class AtendimentoService {

    public void BoasVindas() {
        System.out.println("Boas vindas ao sistema de triagem de chamados!");
    }

    public ChamadoSuporte criarChamado(Scanner sc) {

        String protocolo;
        String cliente;
        int slaMinutos;

        System.out.println("\nCrie o chamado em nosso sistema.");
        System.out.println("Qual o protocolo do seu chamado?");
        protocolo = sc.nextLine();
        System.out.println("\nQual cliente está associado a este chamado?");
        cliente = sc.nextLine();
        System.out.println("\nQual o limide de SLA para este chamado?");
        slaMinutos = sc.nextInt();
        sc.nextLine();
        ChamadoSuporte chamado = new ChamadoSuporte(protocolo, cliente, slaMinutos);
        System.out.println("\nChamado criado com sucesso!");
        
        return chamado;
    }

    public AtividadeAtendimento criarAtividadeAtendimento(Scanner sc, ChamadoSuporte chamado) {

        String descricao;
        int minutosEstimados;
        int minutosExecutados;

        System.out.println("\nAgora crie as atividades que compõem o chamado.");
        System.out.println("\nQual o nome da atividade?:");
        descricao = sc.nextLine();
        System.out.println("\nQual a estimativa em minutos, de execução da atividade?:");
        minutosEstimados = sc.nextInt();
        sc.nextLine();
        System.out.println("\nEm quanto tempo a atividade foi executada?:");
        minutosExecutados = sc.nextInt();
        sc.nextLine();
        AtividadeAtendimento atividade = new AtividadeAtendimento(descricao, minutosEstimados, minutosExecutados);
        chamado.adicionarAtividade(atividade);
        System.out.println("\nAtividade criada e adicionada ao chamado com sucesso!");
        
        return atividade;
    }

    public void resumoFinal(ChamadoSuporte chamado) {
        
        System.out.println("\nResumo final do chamado:");
        System.out.println("--------------------------------------------------");
        System.out.println("\nChamado: "+ chamado.getProtocolo()+ " - " + chamado.getCliente());
        System.out.println("Status: " + chamado.getStatus().getDescricao());
        System.out.println("Progresso: "+ chamado.calcularProgressoAtendimento() + "%");
        System.out.println("Previsão total: " + chamado.calcularPrevisaoTotalMinutos() + "min");
        System.out.println("SLA planejado: " + chamado.getSlaMinutos() + "min");
        System.out.println("Situação SLA: " +chamado.getSituacaoSLA().getDescricao());
        System.out.println("Risco de atendimento: " + chamado.getRiscoAtendimento().getDescricao());
        System.out.println("Índice de consumo do SLA: " + chamado.calcularIndiceConsumoSLA() + "%");
        System.out.println("--------------------------------------------------");
        
    }
}
