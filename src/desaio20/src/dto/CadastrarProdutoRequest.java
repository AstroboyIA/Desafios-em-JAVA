package desaio20.src.dto;

import desaio20.src.domain.DadosAdicionais;
import desaio20.src.domain.enums.CategoriaEstoque;

public class CadastrarProdutoRequest {
    
    private final String nome;
    private final CategoriaEstoque categoria;
    private final double precoBase;
    private final int quantidadeInicial;
    private final DadosAdicionais dadosAdicionais;

    public CadastrarProdutoRequest(String nome, CategoriaEstoque categoria, double precoBase, int quantidadeInicial,
            DadosAdicionais dadosAdicionais) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.quantidadeInicial = quantidadeInicial;
        this.dadosAdicionais = dadosAdicionais;
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


    public int getQuantidadeInicial() {
        return quantidadeInicial;
    }


    public DadosAdicionais getDadosAdicionais() {
        return dadosAdicionais;
    }
    
}
