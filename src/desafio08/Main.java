package desafio08;

import java.util.Scanner;

import desafio08.service.MatriculaService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MatriculaService matriculaService = new MatriculaService();

        matriculaService.wellcome();

        matriculaService.cadastrarAluno(sc);

        matriculaService.cadastrarCurso(sc);

        matriculaService.dadosFinais();
        
    }
}
