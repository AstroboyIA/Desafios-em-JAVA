package desafio07.model;

import java.util.ArrayList;
import java.util.List;

public class Emprestimo {

    List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public double calcularQuantidade() {
        double quantidade = 0.0;
        for (Livro livro : livros) {
            quantidade += livros.size();
        }
        System.out.println("DEBUG calcularQuantidade: " + quantidade); // ADICIONE
        return quantidade;
    }

    public void tamanhoFinal() {
        double quantidade = calcularQuantidade();

        if (quantidade > 3) {
            System.out.println("Empréstimo grande!");
        } else {
            System.out.println("Empréstimo comum!");
        }
    }
}