package desaio20.src.dto;

import java.util.List;
import java.util.Map;

import desaio20.src.domain.enums.CategoriaEstoque;

public class RelatorioEstoqueResponse {
    
    private final int totalProdutos;
    private final int produtosAbaixoDoMinimo;
    private final int produtosSemEstoque;
    private final double valorTotalEmEstoque;
    private final Map<CategoriaEstoque, Integer> quantidadePorCategoria;
    private final List<String> alertasValidade;
    
}
