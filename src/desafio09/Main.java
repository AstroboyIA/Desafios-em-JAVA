package desafio09;

import java.util.Scanner;

import desafio09.model.Boletim;
import desafio09.service.BoletimService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Boletim boletim = new Boletim();
        
        BoletimService service = new BoletimService();

        service.welcome();
        
        service.cadastrarAluno(sc);

        service.cadastrarNotas(sc, boletim);

        service.exibirBoletim(boletim);

        sc.close();

    }
}
