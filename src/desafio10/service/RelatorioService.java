package desafio10.service;

import java.util.Scanner;

import desafio10.model.Avaliacao;
import desafio10.model.Funcionario;
import desafio10.model.Relatorio;

public class RelatorioService {

    Funcionario funcionario;

    public void welcome() {
        System.out.println("Bem-vindo ao sistema de relatórios de avaliações!");
    }

    public Funcionario cadastrarFuncionario(Scanner sc, Funcionario funcionario) {
        String nomeFuncionario;
        System.out.println("Para continuar, cadastre um funcionário!");

        System.out.println("Insira o nome do funcionário:");
        nomeFuncionario = sc.nextLine();
        funcionario = new Funcionario(nomeFuncionario);
        return funcionario;
    }

    public void cadastrarNotas(Scanner sc, Relatorio relatorio) {
        double nota = 0.0;
        int opcao;
        System.out.println("Para continuar, insira uma nota de avaliação para o funcionário:");
        do{
            System.out.println("Insira a nota:");
            nota = sc.nextDouble();
            sc.nextLine();
            Avaliacao avaliacao = new Avaliacao(nota);
            relatorio.adicionarAvaliacao(avaliacao);
            System.out.println("Deseja iserir outra nota?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextInt();
            sc.nextLine();
        
        } while (opcao == 1);
    }

    public void exibirSatisfacao(Relatorio relatorio){
        System.out.println("----- Relatório de Satisfação -----");
        System.out.println("O funcionário: " + funcionario.getNome() + " ficou com a média de notas avaliativas "+ relatorio.calcularMedia() + " que é considerada " + relatorio.isDesemprenhoSatisfatorio() + ".");
    }
}
