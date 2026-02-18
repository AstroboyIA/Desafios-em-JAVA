package main.desafio16.model;

public enum StatusChamado {
    ABERTO("Aberto"),
    EM_ATENDIMENTO("Em atendimento"),
    RESOLVIDO("Resolvido");

    private String description;

    private StatusChamado(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
