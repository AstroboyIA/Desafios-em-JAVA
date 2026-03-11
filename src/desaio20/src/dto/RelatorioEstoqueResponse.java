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

    private RelatorioEstoqueResponse(Builder builder) {
    
        this.totalProdutos = builder.totalProdutos;
        this.produtosAbaixoDoMinimo = builder.produtosAbaixoDoMinimo;
        this.produtosSemEstoque = builder.produtosSemEstoque;
        this.valorTotalEmEstoque = builder.valorTotalEmEstoque;
        this.quantidadePorCategoria = builder.quantidadePorCategoria;
        this.alertasValidade = builder.alertasValidade;
        
    }

    public static class Builder {

        private int totalProdutos;
        private int produtosAbaixoDoMinimo;
        private int produtosSemEstoque;
        private double valorTotalEmEstoque;
        private Map<CategoriaEstoque, Integer> quantidadePorCategoria;
        private List<String> alertasValidade;

        public Builder totalProdutos(int val) { this.totalProdutos = val; return this;}

        public Builder produtosAbaixoDoMinimo(int val) { this.produtosAbaixoDoMinimo = val; return this;}

        public Builder produtosSemEstoque(int val) { this.produtosSemEstoque = val; return this;}

        public Builder valorTotalEmEstoque(double val) { this.valorTotalEmEstoque = val; return this;}

        public Builder quantidadePorCategoria(Map<CategoriaEstoque, Integer> val) { this.quantidadePorCategoria = val; return this;}

        public Builder alertasValidade(List<String> val) { this.alertasValidade = val; return this;}

        public RelatorioEstoqueResponse build() {
            return new RelatorioEstoqueResponse(this);
        }
    }

}