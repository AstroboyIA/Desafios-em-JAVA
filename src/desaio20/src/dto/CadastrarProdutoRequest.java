package desaio20.src.dto;

import desaio20.src.domain.DadosAdicionais;
import desaio20.src.domain.enums.CategoriaEstoque;

public class CadastrarProdutoRequest {
    
    private final String nome;
    private final CategoriaEstoque categoria;
    private final double precoBase;
    private final int quantidadeInicial;
    private final DadosAdicionais dadosAdicionais;
    
}
