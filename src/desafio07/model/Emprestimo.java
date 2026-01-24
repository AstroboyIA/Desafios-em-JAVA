package desafio07.model;

import java.util.ArrayList;
import java.util.List;

public class Emprestimo {

    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public int calcularQuantidade() {
        int quantidade = 0;
        for (Livro livro : livros) {
            quantidade += 1;
        }
        return quantidade;
    }
}