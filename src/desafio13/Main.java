package main.desafio13;

import java.util.Scanner;

import main.desafio13.backend.model.Projeto;
import main.desafio13.backend.service.ProjetoService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProjetoService service = new ProjetoService();

        Projeto projeto = new Projeto("");
        
        service.BoasVindas();

        projeto = service.cadastrarProjeto(sc, projeto);

        service.cadastarTarefas(sc, projeto);

        service.resumoProjeto(projeto);

        sc.close();

    }
}
