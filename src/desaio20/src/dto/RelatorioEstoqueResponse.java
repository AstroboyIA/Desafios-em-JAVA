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
    
    public int getTotalProdutos() {
        return totalProdutos;
    }

    public int getProdutosAbaixoDoMinimo() {
        return produtosAbaixoDoMinimo;
    }

    public int getProdutosSemEstoque() {
        return produtosSemEstoque;
    }

    public double getValorTotalEmEstoque() {
        return valorTotalEmEstoque;
    }

    public Map<CategoriaEstoque, Integer> getQuantidadePorCategoria() {
        return quantidadePorCategoria;
    }

    public List<String> getAlertasValidade() {
        return alertasValidade;
    }

    public RelatorioEstoqueResponse(int totalProdutos, int produtosAbaixoDoMinimo, int produtosSemEstoque,
            double valorTotalEmEstoque, Map<CategoriaEstoque, Integer> quantidadePorCategoria,
            List<String> alertasValidade) {
        this.totalProdutos = totalProdutos;
        this.produtosAbaixoDoMinimo = produtosAbaixoDoMinimo;
        this.produtosSemEstoque = produtosSemEstoque;
        this.valorTotalEmEstoque = valorTotalEmEstoque;
        this.quantidadePorCategoria = quantidadePorCategoria;
        this.alertasValidade = alertasValidade;
    }
    
}