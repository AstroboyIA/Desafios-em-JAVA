package desafio18.model;

public enum TipoAcao {
    
    CONTENCAO(3, false),
    MITIGACAO(2, false),
    CORRECAO(4, true),
    COMUNICACAO(1, false);

    private final int pesoImpacto;
    private final boolean risco;

    public int getPesoImpacto() {
        return pesoImpacto;
    }

    public boolean reduzRisco() {
        return risco;
    }

    TipoAcao(int pesoImpacto, boolean risco) {
        this.pesoImpacto = pesoImpacto;
        this.risco = risco;
    }

}
