package desaio20.src.dto;

import java.util.UUID;

import desaio20.src.domain.Produto;
import desaio20.src.domain.enums.CategoriaEstoque;
import desaio20.src.domain.enums.TipoEvento;
import desaio20.src.repository.ProdutoRepository;

public class ProdutoResponse {

    private final String id;
    private final String nome;
    private final CategoriaEstoque categoria;
    private final double precoBase;
    private final double precoFinal;
    private final int quantidadeEmEstoque;
    private final String resumoDadosAdicionais;
    private final boolean abaixoDoMinimo;

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

    public double getPrecoFinal() {
        return precoFinal;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public String getResumoDadosAdicionais() {
        return resumoDadosAdicionais;
    }

    public boolean isAbaixoDoMinimo() {
        return abaixoDoMinimo;
    }

    public ProdutoResponse(String id, String nome, CategoriaEstoque categoria, double precoBase, double precoFinal,
            int quantidadeEmEstoque, String resumoDadosAdicionais, boolean abaixoDoMinimo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoFinal = precoFinal;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.resumoDadosAdicionais = resumoDadosAdicionais;
        this.abaixoDoMinimo = abaixoDoMinimo;
    }

}