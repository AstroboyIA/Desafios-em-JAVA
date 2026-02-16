package desafio14.model;

public enum TamanhoProjeto {
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private TamanhoProjeto(String descricao) {
        this.descricao = descricao;
    }
}
