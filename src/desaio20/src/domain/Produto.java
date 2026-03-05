package desaio20.src.domain;

import desaio20.src.domain.enums.CategoriaEstoque;

public class Produto <T extends DadosAdicionais> {
    
    private final String id;
    private final String nome;
    private final CategoriaEstoque categoria;
    private double precoBase;
    private int quantidadeEmEstoque;
    private final T dadosAdicionais;

    public Produto(String id, String nome, double precoBase, int quantidadeEmEstoque, T dadosAdicionais) {

        if (id.isEmpty() || id == null) {
            throw new IllegalArgumentException("O ID precisa ser informado!");
        }

        if (nome.isEmpty() || nome == null) {
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
        this.dadosAdicionais = dadosAdicionais;

    }
    
}
