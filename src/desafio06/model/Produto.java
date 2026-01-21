package desafio06.model;

public class Produto {
    private String nome;
    private Double preco;

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}

// Finalizado