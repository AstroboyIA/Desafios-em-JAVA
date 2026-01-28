package desafio10.service;

import java.util.Scanner;

import desafio10.model.Funcionario;
import desafio10.model.Relatorio;

public class RelatorioService {

    Funcionario funcionario;
    Relatorio relatorio;


    public void welcome(){
        System.out.println("Bem-vindo ao sistema de relatórios de avaliações!");
    }

    public Funcionario cadastrarFuncionario(Scanner sc, Funcionario funcionario){
        String nomeFuncionario;
        System.out.println("Para continuar, cadastre um funcionário!");

        System.out.println("Insira o nome do funcionário:");
        nomeFuncionario = sc.nextLine();
        funcionario = new Funcionario(nomeFuncionario);
        return funcionario;
    }

    public double cadastrarNotas(Scanner sc, Relatorio relatorio){
        double nota;
        System.out.println("Insira a nota do funcionário:");
        nota = sc.nextDouble();
        relatorio = new Relatorio();
        return nota;
    }
}
