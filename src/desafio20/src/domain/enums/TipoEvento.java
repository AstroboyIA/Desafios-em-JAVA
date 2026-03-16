package desafio20.src.domain.enums;

public enum TipoEvento {
    
    PRODUTO_CADASTRADO("Produto cadastrado!"),
    ENTRADA_ESTOQUE("Produto adicionado ao estoque!"),
    SAIDA_ESTOQUE("Produto retirado do estoque"),
    ESTOQUE_MINIMO_ATINGIDO("Quantidade minima atingida!"),
    ESTOQUE_ZERADO("Sem produtos no estoque."),
    VALIDADE_PROXIMA("A válidade do produto está proxima do vencimento.");

    private final String descricao;

    private TipoEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}