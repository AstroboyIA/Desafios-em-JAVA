package desafio05.model;

public class Produto {
    String produto;
    Double preco; // preco nao pode ser negativo

  public Double getPreco() {
        return preco;
    }

  public Produto (String produto, Double preco) {
        this.produto = produto;
        this.preco = preco;
    }
}