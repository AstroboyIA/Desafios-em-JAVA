package desafio12;

import java.util.Scanner;

import desafio12.model.Projeto;
import desafio12.model.Tarefa;
import desafio12.service.ProjetoService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Projeto projeto = new Projeto("");

        ProjetoService projetoService = new ProjetoService();

        projetoService.boasvindas();
        
        projetoService.cadastrarProjeto(sc);

        projetoService.adicionarTarefa(projeto, sc);

        projetoService.resumoProjeto(projeto);

        sc.close();
    
    }
}