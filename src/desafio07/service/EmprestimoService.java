package desafio07.service;

import java.util.Scanner;

import desafio07.model.Leitor;
import desafio07.model.Livro;
import desafio07.model.Emprestimo;

public class EmprestimoService {

    public Emprestimo emprestimo = new Emprestimo();

    public void saldacaoInicial() {
        System.out.println("Bem vindo ao sistema de empréstimo de livros!");
    }

    public void dadosLeitor(Scanner sc) {
        String nomeLeitor;
        System.out.println("Para continuar com o empréstimo, por favor cadastre um usuário:");
        System.out.println("Por favor insira o nome do usuário:");
        nomeLeitor = sc.nextLine();
        Leitor leitor = new Leitor(nomeLeitor);
        System.out.println("Usuário " + leitor.getNome() + " cadastrado com sucesso!");
    }

    public void cadastrarLivro(Scanner sc) {
        String nomeLivro;
        String autorLivro;
        byte opcao;

        System.out.println("Para continuar, cadastre o livro que deseja pegar emprestado!");

        do {
            System.out.println("Por favor insira o nome do livro:");
            nomeLivro = sc.nextLine();
            System.out.println("Por favor insira o autor do livro:");
            autorLivro = sc.nextLine();
            System.out.println("Livro cadastrado com sucesso!");

            Livro livro = new Livro(nomeLivro, autorLivro);
            emprestimo.adicionarLivro(livro);

            System.out.println("Deseja cadastrar outro livro?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

        } while (opcao == 1);
    }

    public void finalizarEmprestimo() {

        System.out.println("Finalizando empréstimo...");

        System.out.println("Empréstimo finalizado com sucesso! Seus livros estão prontos para serem retirados.");

        int quantidade = emprestimo.calcularQuantidade();
        
        if (quantidade > 3) {
            System.out.println("Empréstimo grande!");
        } else {
            System.out.println("Empréstimo comum!");
        }

    }
}
