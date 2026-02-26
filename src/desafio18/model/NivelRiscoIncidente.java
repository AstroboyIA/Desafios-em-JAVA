package desafio18.model;

public enum NivelRiscoIncidente {
    
    CONTROLADO("Controlado"),
    ATENCAO("Atenção"),
    CRITICO("Crítico"),
    COLAPSO("Colapso");
    
    String descricao;

    NivelRiscoIncidente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}