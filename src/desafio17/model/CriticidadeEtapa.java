package desafio17.model;

public enum CriticidadeEtapa {
    BAIXA(1, "baixa"),
    MEDIA(2, "média"),
    ALTA(3, "alta");

    private final int peso;
    private final String descricao;

    CriticidadeEtapa(int peso, String descricao) {
        this.peso = peso;
        this.descricao = descricao;
    }

    public int getPeso() {
        return peso;
    }

    public String getDescricao() {
        return descricao;
    }
}