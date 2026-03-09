package desaio20.src.domain;

import java.util.UUID;

import desaio20.src.domain.enums.CategoriaEstoque;

public class Produto<T extends DadosAdicionais> {

    private final String id;
    private final String nome;
    private final CategoriaEstoque categoria;
    private double precoBase;
    private int quantidadeEmEstoque;
    private final T dadosAdicionais;

    public Produto(String id, String nome, CategoriaEstoque categoria, double precoBase, int quantidadeEmEstoque,
            T dadosAdicionais) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("O ID precisa ser informado!");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome precisa ser informado!");
        }

        if (precoBase <= 0) {
            throw new IllegalArgumentException("O preço precisa ser maior que 0.");
        }

        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("A quantidade de produtos em estoque não pode ser negativa.");
        }

        this.id = id;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
        this.dadosAdicionais = dadosAdicionais;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaEstoque getCategoria() {
        return categoria;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public T getDadosAdicionais() {
        return dadosAdicionais;
    }

    public void adicionarQuantidadeAoEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0");
        }
        this.quantidadeEmEstoque += quantidade;
    }

    public void removerQuantidadeDoEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0");
        }
        this.quantidadeEmEstoque -= quantidade;
    }

    public boolean isAbaixoDoMinimo() {
        return this.quantidadeEmEstoque < this.categoria.getQuantidadeMinimaRecomendada();
    }
}
