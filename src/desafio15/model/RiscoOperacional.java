package main.desafio15.model;

public enum RiscoOperacional {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String risco;

    private RiscoOperacional(String risco) {
        this.risco = risco;
    }

    public String getRisco() {
        return risco;
    }
}
