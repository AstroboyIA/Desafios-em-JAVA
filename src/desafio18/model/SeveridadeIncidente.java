package desafio18.model;

public enum SeveridadeIncidente {
    
    BAIXA(1, "Baixa"),
    MODERADA(2, "Moderada"),
    ALTA(3, "Alta"),
    CRITICA(5, "Crítica");

    private final String descricao;
    private final int multiplicador;

    public String getDescricao() {
        return descricao;
    }
    
    SeveridadeIncidente(int multiplicador, String descricao) {
        this.multiplicador = multiplicador;
        this.descricao = descricao;
    }

    public int getMultiplicadorPenalidade() {
        return multiplicador;
    }
}
