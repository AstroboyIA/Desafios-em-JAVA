package desafio03;

import desafio03.poo.Aluno;
import desafio03.poo.Professor;
import desafio03.poo.heranca.Pessoa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("- Bem vindo ao Sistema de Gestão Escolar -");

        ArrayList<Pessoa> listaEscolar = new ArrayList<>();

        boolean confirmar = true;
        while (confirmar) {
            System.out.println("Escolhar uma das opções abaixo:");
            System.out.println(
                    "1 - Cadastrar Aluno \n" +
                    "2 - Cadastrar Professor \n" +
                    "3 - Mostrar pessoas cadastradas \n" +
                    "4 - Sair");

            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("- Criando cadastro de Aluno -");
                    System.out.println("Digite o nome do Aluno: ");
                    String nomeAluno = entrada.nextLine();
                    System.out.println("Digite o ID do Aluno: ");
                    int idAluno = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Digite a nota final do Aluno: ");
                    double nota = entrada.nextDouble();
                    entrada.nextLine();

                    Pessoa aluno = new Aluno(nomeAluno, idAluno, nota);
                    aluno.setNome(nomeAluno);
                    aluno.setId(idAluno);
                    aluno.setNota(nota);
                    listaEscolar.add(aluno);
                    System.out.println("Cadastro criado com sucesso!");
                    break;

                case 2:
                    System.out.println("- Criando cadastro de Professor -");
                    System.out.println("Digite o nome do Professor: ");
                    String nomeProfessor = entrada.nextLine();
                    System.out.println("Digite o ID do Professor: ");
                    int idProfessor = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Digite a disciplina do Professor: ");
                    String disciplina =  entrada.nextLine();

                    Pessoa professor = new Professor(nomeProfessor, idProfessor, disciplina);
                    professor.setNome(nomeProfessor);
                    professor.setId(idProfessor);
                    professor.setDisciplina(disciplina);
                    listaEscolar.add(professor);
                    System.out.println("Cadastro criado com sucesso!");
                    break;

                case 3:
                    if (!listaEscolar.isEmpty()){
                        System.out.println("O cadastro está vazio!");
                    }else{
                        System.out.println("\n -Cadastro Atual -");
                        for ( Pessoa p : listaEscolar) {
                            p.exibirInfo();
                        }
                    }
                    break;

                case 4:
                    confirmar = false;
                    System.out.println("Encerrando Sistema");
                    break;

                default:
                    System.out.println("Nenhuma opção válida selecionada, tente novamente!");
            }
        }
        entrada.close();
    }
}