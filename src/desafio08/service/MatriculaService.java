package desafio08.service;

import java.util.Scanner;

import desafio08.model.Aluno;
import desafio08.model.Curso;
import desafio08.model.Matricula;

public class MatriculaService {

    private Matricula matricula;

    public MatriculaService() {
        this.matricula = new Matricula();
    }

    public void welcome() {
        System.out.println("Bem vindo ao sistema de matrícula!");
    }

    public void cadastrarAluno(Scanner sc) {
        String nomeAluno;
        System.out.println("Para continuar, cadastre um aluno:");
        nomeAluno = sc.nextLine();
        Aluno aluno = new Aluno(nomeAluno);
        System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso!");
    }

    public Matricula cadastrarCurso(Scanner sc) {
        String nomeCurso;
        int cargaHoraria;
        byte opcao;
        System.out.println("Para continuar, cadastre o curso do aluno:");

        do {
            System.out.println("Nome do curso:");
            nomeCurso = sc.nextLine();
            System.out.println("Qual é a carga horária do curso?:");
            cargaHoraria = sc.nextInt();
            sc.nextLine();
            System.out.println("Curso cadastrado com sucesso!");

            Curso curso = new Curso(nomeCurso, cargaHoraria);
            this.matricula.adicionarCursos(curso);

            System.out.println("Deseja cadastrar outro curso ?:");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();
        } while (opcao == 1);

        return matricula;
    }

    public void dadosFinais() {
        boolean classificarMatricula = matricula.classificarMatricula();

        System.out.println("Cadastro finalizado!");

        if (classificarMatricula) {
            System.out.println("Matrícula completa!");
        } else {
            System.out.println("Matrícula simples!");
        }
    }
}