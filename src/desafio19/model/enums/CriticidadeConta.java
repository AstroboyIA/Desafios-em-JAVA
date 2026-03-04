package desafio19.model.enums;

public enum CriticidadeConta {
    
    ESTAVEL("Estável"),
    MONITORADA("Monitorada"),
    CRITICA("Crítica"),
    BLOQUEADA("Bloqueada");

    public String descricao;

    private CriticidadeConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
