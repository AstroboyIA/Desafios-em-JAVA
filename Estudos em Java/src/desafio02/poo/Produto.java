package desafio02.poo;

public class Produto {

    private String nome;
    private double preco;
    private int quantidade;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void exibirDetalhes(){
        System.out.println("O produto: " + nome + " tem " + quantidade + " em estoque, com o valor por unidade de: " + preco);
    }
}