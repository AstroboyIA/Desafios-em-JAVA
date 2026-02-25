package desafio14.service;

import java.util.List;
import java.util.Scanner;

import desafio14.model.Projeto;
import desafio14.model.Tarefa;

public class ProjetoService {
    
    public void BoasVindas() {
        System.out.println("Bem vindo ao sistema de gerenciamento de projetos");
    }

    public Projeto cadastrarProjeto(Scanner sc) {
        String nomeProjeto;
        int prazoHoras;
        System.out.println("Cadastre seu projeto e as suas tarefas abaixo!");
        System.err.println("Qual o nome do seu projeto?");
        nomeProjeto = sc.nextLine();
        System.out.println("Qual o prazo do seu projeto?");
        prazoHoras = sc.nextInt();
        sc.nextLine();
        Projeto projeto = new Projeto(prazoHoras, nomeProjeto);
        System.out.println("Projeto cadastrado com sucesso!");

        return projeto;
    }

    public void cadastrarTarefas(Scanner sc, Projeto projeto) {
        String descricao;
        int horasEstimadas;
        int horasConcluidas;
        byte opcao;

        System.out.println("Agora cadastre as tarefas do seu projeto");
        do{
            System.out.println("Qual a descrição da tarefa?");
            descricao = sc.nextLine();
            System.out.println("Qual a estimativa em horas, da conclusão da tarefa ?");
            horasEstimadas = sc.nextInt();
            sc.nextLine();
            System.out.println("Em quantas horas a tafera foi concluida?");
            horasConcluidas = sc.nextInt();
            sc.nextLine();
            Tarefa tarefa = new Tarefa(descricao, horasEstimadas, horasConcluidas);
            projeto.cadastrarTarefa(tarefa);
            System.out.println("Tarefa cadastrada com sucesso!");
            System.out.println("Deseja cadastrar outra tarfa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();
        }while(opcao == 1);
    }

    public void resumoFinal(Projeto projeto) {
        System.out.println("Projeto: " + projeto.getNome());
        System.out.println("Status: " + projeto.calcularStatus().getDescricao());
        System.out.println("Progresso: " + projeto.percentualProjeto()+"%");
        System.out.println("Previsao de esforço: " + projeto.calcularPrevisaoHorasFinais()+"H");
        System.out.println("Prazo planejado: " + projeto.getPrazoHoras()+"H");
        System.out.println("Situação do prazo: " + projeto.calcularSituacaoPrazo().getDescricao());
        System.out.println("Concluão Real (prazo): " + projeto.calcularPercentualConclusaoReal()+"%");
    }
}
