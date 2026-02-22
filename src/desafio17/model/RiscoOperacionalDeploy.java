package desafio17.model;

public enum RiscoOperacionalDeploy {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private RiscoOperacionalDeploy(String descricao) {
        this.descricao = descricao;
    }
}
