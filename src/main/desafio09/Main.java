package main.desafio09;

import java.util.Scanner;

import main.desafio09.model.Boletim;
import main.desafio09.service.BoletimService;

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
