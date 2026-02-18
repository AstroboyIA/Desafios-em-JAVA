package main.desafio15.model;

public enum SituacaoPrazoPedido {
    ADIANTADO("Adiantado"),
    NO_PRAZO("No prazo"),
    ATRASADO("Atrasado");

    private String situacao;

    private SituacaoPrazoPedido(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacaoPrazo() {
        return situacao;
    }

}
