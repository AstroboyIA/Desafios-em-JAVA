package main.desafio11;

import java.util.Scanner;

import main.desafio11.model.Aluno;
import main.desafio11.model.RegistroTreino;
import main.desafio11.model.Treino;
import main.desafio11.service.TreinoService;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        TreinoService treinoService = new TreinoService();
        RegistroTreino RegistroTreino = new RegistroTreino();

        treinoService.welcome();

        Aluno aluno = treinoService.registrarAluno(sc);

        treinoService.adicionarTreino(RegistroTreino,sc);

        treinoService.resumoFinalTreinos(RegistroTreino, aluno);

        sc.close();
    }
}