package desafio04.poo.heranca;

public class Produto {

    protected String nome;
    protected double precoBase;

    public Produto(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void exibirDados() {
        System.out.println(
                "Nome do produto: " + nome + "\n" +
                        "Preco do produto: " + precoBase);
    }
}
