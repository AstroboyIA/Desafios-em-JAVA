package desaio20.src.dto;

import desaio20.src.domain.enums.CategoriaEstoque;

public class ProdutoResponse {
    
    private final String id;
    private final String nome;
    private final CategoriaEstoque categoria;
    private final double precoBase;
    private final double precoFinal;
    private final int quantidadeEmEstoque;
    private final String resumoDadosAdicionais;
    private final boolean abaixoDoMinimo;

}
