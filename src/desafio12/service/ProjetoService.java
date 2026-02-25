package desafio12.service;

import java.util.Scanner;

import desafio12.model.Projeto;
import desafio12.model.TamanhoProjeto;
import desafio12.model.Tarefa;

public class ProjetoService {

    public void boasvindas() {
        System.out.println("Boas vindas ao sistema de gerenciamento de projetos!");
    }

    public Projeto cadastrarProjeto(Scanner sc) {
        String nomeProjeto;
        System.out.println("Cadastre seu projeto em nosso sistema!");
        System.out.println("Qual seu projeto?");
        nomeProjeto = sc.nextLine();
        Projeto projeto = new Projeto(nomeProjeto);

        System.out.println("Projeto cadastrado com sucesso!");

        return projeto;
    }

    public void adicionarTarefa(Projeto projeto, Scanner sc) {
        String descricaoTarefa;
        int horasEstimadas;
        byte opcao;

        System.out.println("Vamos adicionar tarefas ao projeto.");

        do {
            System.out.println("Insira uma tarefa:");
            descricaoTarefa = sc.nextLine();
            System.out.println("Em quantas horas sua tarefa deve ser concluida?");
            horasEstimadas = sc.nextInt();
            sc.nextLine();

            Tarefa tarefa = new Tarefa(descricaoTarefa, horasEstimadas);
            projeto.adicionarTarefa(tarefa);
            System.out.println("Tarefa adicionada com sucesso!");

            System.out.println("Deseja adicionar outra tarefa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

        } while (opcao == 1);
    }

    public void resumoProjeto(Projeto projeto) {

        System.out.println("---Resumo do projeto---");
        System.out.println("Projeto :" + projeto.getNome());
        System.out.println("Total de horas : " + projeto.calcularTotalHorasEstimadas());

        TamanhoProjeto tamanho = projeto.calcularTamanho();
        System.out.println("Classificação :" + tamanho.getDescricao());
    }
}