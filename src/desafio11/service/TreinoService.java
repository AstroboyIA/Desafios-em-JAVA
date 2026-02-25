package desafio11.service;

import java.util.Scanner;

import desafio11.model.Aluno;
import desafio11.model.RegistroTreino;
import desafio11.model.Treino;

public class TreinoService {
    

    public void welcome() {
        System.out.println("Bem-vindo ao sistema de registro de treinos!");
    }

    public Aluno registrarAluno(Scanner sc) {
        String nomeAluno;
        System.out.println("Para registrar um treino, primeiro, registre um aluno!");
        System.out.println("Digite o nome do aluno:");
        nomeAluno = sc.nextLine();

        return new Aluno(nomeAluno);
    }

    public void adicionarTreino(RegistroTreino RegistroTreino, Scanner sc) {
        String descricaoTreino;
        int duracaoTreino;
        byte opcao;
        System.out.println("Agora, adicione um treino para o aluno: ");

        do {
            System.out.println("Qual o tipo de treino?");
            descricaoTreino = sc.nextLine();

            System.out.println("Qual a duração do treino?");
            duracaoTreino = sc.nextInt();
            sc.nextLine();

            Treino treino = new Treino(descricaoTreino, duracaoTreino);
            RegistroTreino.adicionarTreino(treino);
            
            System.out.println("Deseja adicionar mias um treino?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

        } while (opcao == 1);

        System.out.println("Treino adicionado com sucesso!");
    }

    public void resumoFinalTreinos(RegistroTreino RegistroTreino, Aluno aluno) {
        boolean isConsistente = RegistroTreino.isTreinoConsistente();
        System.out.println("Resumo dos treinos:");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Treinos estimados : " + RegistroTreino.getTreinos().size());
        System.out.println("Duração estimada dos trenos: " + RegistroTreino.fullTimeTreino() + " minutos");
        String statusTreino;
        if (isConsistente) {
            statusTreino = "Consistente!";
        } else {
            statusTreino = "Insuficiente!";
        }
        System.out.println("O treino dessa semana foi considerado: " + statusTreino);
    }
}