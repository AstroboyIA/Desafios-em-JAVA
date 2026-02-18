package main.desafio16.model;

public enum RiscoAtendimento {
    BAIXO("Baixo Risco"),
    MEDIO("Médio Risco"),
    ALTO("Alto Risco");

    private String descricao;

    private RiscoAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}