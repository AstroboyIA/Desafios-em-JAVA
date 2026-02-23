package desafio16.model;

public enum StatusChamado {
    ABERTO("Aberto"),
    EM_ATENDIMENTO("Em atendimento"),
    RESOLVIDO("Resolvido");

    private String descricao;

    private StatusChamado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
