package desafio14;

import java.util.Scanner;

import desafio14.model.Projeto;
import desafio14.service.ProjetoService;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        ProjetoService service = new ProjetoService();

        service.BoasVindas();

        Projeto projeto = service.cadastrarProjeto(sc);

        service.cadastrarTarefas(sc, projeto);

        service.resumoFinal(projeto);

        sc.close();
    }
}
