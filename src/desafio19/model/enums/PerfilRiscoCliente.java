package desafio19.model.enums;

public enum PerfilRiscoCliente {
    
    BAIXO(1),
    MODERADO(2),
    ALTO(5);

    private int multiplicadorRisco;

    public int getMultiplicadorRisco() {
        return multiplicadorRisco;
    }

    private PerfilRiscoCliente(int multiplicadorRisco) {
        this.multiplicadorRisco = multiplicadorRisco;
    }

}
