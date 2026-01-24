package desafio07;

import java.util.Scanner;

import desafio07.model.Emprestimo;
import desafio07.service.EmprestimoService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Emprestimo emprestimo = new Emprestimo();
        
        EmprestimoService service = new EmprestimoService();

        service.saldacaoInicial();

        service.dadosLeitor(sc);

        service.cadastrarLivro(sc);

        service.emprestimo.tamanhoFinal();

        sc.close();

    }
}
