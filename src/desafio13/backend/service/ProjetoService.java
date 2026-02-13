package desafio13.backend.service;

import java.util.Scanner;

import desafio13.backend.model.Projeto;
import desafio13.backend.model.Tarefa;

public class ProjetoService {

    public void BoasVindas() {
        System.out.println("Bem vindo ao sitema de gerenciamento de projetos!");
    }

    public void cadastrarProjeto(Scanner sc) {
        String nomeProjeto;
        System.out.println("Cadastre seu projeto em nosso sistema!");
        System.out.println("Qual o nome do seu projeto?");
        nomeProjeto = sc.nextLine();
        System.out.println("O nome do projeto foi cadastrado!");
    }

    public void cadastarTarefas(Scanner sc, Projeto projeto) {
        String nomeTarefa;
        int horasEstimadas;
        int horasConcluidas;
        byte escolha;

        System.out.println("Para continuar, precisamos de mais intormações sobre o projeto.");
        System.out.println("Quais tarefas estão incluidas no seu projeto?");

        do {
            System.err.println("Insira a tarefa:");
            nomeTarefa = sc.nextLine();
            System.out.println("Qual a estimativa de horas para conclusão da tarefa?");
            horasEstimadas = sc.nextInt();
            sc.nextLine();
            System.out.println("Em quantas horas sua tarefa foi concluida?");
            horasConcluidas = sc.nextInt();
            sc.nextLine();
            Tarefa tarefa = new Tarefa();
            tarefa.cadastrarTarefa(nomeTarefa, horasEstimadas, horasConcluidas);
            System.out.println("A tarefa foi cadastrada!");
            System.out.println("Deseja cadastrar outra tarefa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            escolha = sc.nextByte();
            sc.nextLine();
        } while (escolha == 1);
    }

    public void resumoProjeto(Projeto Projeto) {
        System.out.println("Projeto: " + Projeto.getNome());
        System.err.println("Status: " + Projeto.calcularStatus());
        System.out.println("Progresso: " + Projeto.percentualProjeto() + "%");
        System.out.println("Tamanho: " + Projeto.calcularTamanho());
    }
}
