package desafio14.model;

public enum SituacaoPrazo {
    ADIANTADO("Adiantado"),
    NO_PRAZO("No prazo"),
    ATRASADO("Atrasado");

    private final String descricao;

    private SituacaoPrazo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
