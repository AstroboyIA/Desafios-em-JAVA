package desafio09.service;

import java.util.Scanner;

import desafio09.model.Aluno;
import desafio09.model.Boletim;
import desafio09.model.Nota;

public class BoletimService {

    Aluno aluno;

    public void welcome(){
        System.out.println("Bem-vindo ao sistema de boletim escolar!");
    }

    public Aluno cadastrarAluno(Scanner sc) {
        String nomeAluno;

        System.out.println("Para criar o boletim, primeiro, precisamos cadastrar o aluno.");

        System.out.println("Por favor, insira o nome do aluno:");
        nomeAluno = sc.nextLine();
        aluno = new Aluno(nomeAluno);

        System.out.println("Aluno cadastrado com sucesso!");
        return aluno;
    }

    public void cadastrarNotas(Scanner sc, Boletim boletim){
        byte opcao;
        System.out.println("Por favor, insira as notas do aluno:");

        do{
            System.out.println("Insira a nota:");
            double nota = sc.nextDouble();
            sc.nextLine();
            boletim.adicionarNota(new Nota(nota));
            System.out.println("Nota adicionada com sucesso!");
            System.out.println("Deseja adicionar outra nota ao boletim?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

        }while(opcao == 1);
    }

    public void exibirBoletim(Boletim boletim){
        System.out.println("----- Boletim do Aluno -----");
        System.out.println("Nome do Aluno: " + aluno.getNome());
        System.out.println("Média Final: " + boletim.calcularMedia());
        if(boletim.isAprovado()){
            System.out.println("Aprovado");
        } else {
            System.out.println("Reprovado");
        }
    }
}