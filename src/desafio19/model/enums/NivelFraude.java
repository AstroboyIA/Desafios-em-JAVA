package desafio19.model.enums;

public enum NivelFraude {
    
    NORMAL("Normal"),
    SUSPEITA("Suspeita"),
    ALTO_RISCO("Alto risco"),
    FRAUDE_CONFIRMADA("Fraude confirmada");

    private String descricao;

    private NivelFraude(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
