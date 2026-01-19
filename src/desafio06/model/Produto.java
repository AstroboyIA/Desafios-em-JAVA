package desafio06.model;

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

        do {
            if (preco < 0) {
                System.out.println("O preço não pode ser negativo, informe um valor válido!");
            }
        } while (preco < 0);
    }

}

// Finalizado