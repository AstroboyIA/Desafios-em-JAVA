package desafio16.main.service;

import java.util.Scanner;

import main.desafio16.model.AtividadeAtendimento;
import main.desafio16.model.ChamadoSuporte;

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
        System.out.println("Chamado criado com sucesso!");
        
        return chamado;
    }

    public AtividadeAtendimento criarAtividadeAtendimento(Scanner sc) {

        String descricao;
        int minutosEstimados;
        int minutosExecutados;

        System.out.println("\nAgora crie as atividades que compõem o chamado.");
        System.out.println("\nQual o nome da atividade?:");
        descricao = sc.nextLine();
        System.out.println("Qual a estimativa em minutos, de execução da atividade?:");
        minutosEstimados = sc.nextInt();
        sc.nextLine();
        System.out.println("Em quanto tempo a atividade foi executada?:");
        minutosExecutados = sc.nextInt();
        sc.nextLine();
        AtividadeAtendimento atividade = new AtividadeAtendimento(descricao, minutosEstimados, minutosExecutados);
        
        return atividade;
    }

    public void resumoFinal() {
        
    }
}
