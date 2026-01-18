package desafio06.Model;

public class Produto {
    public String nome;
    public Double preco;

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