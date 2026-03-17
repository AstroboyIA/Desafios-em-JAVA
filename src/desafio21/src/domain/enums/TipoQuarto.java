package desafio21.src.domain.enums;

public enum TipoQuarto {
    
    STANDARD(2,1.0),
    LUXO(2,1.5),
    SUITE(4,2.0),
    SUITE_PRESIDENCIAL(6,3.5);

    private final int capacidadePadrao;
    private final double multiplicadorTarifa;

    public double getMultiplicadorTarifa() {
        return multiplicadorTarifa;
    }

    public int getCapacidadePadrao() {
        return capacidadePadrao;
    }

    private TipoQuarto(int capacidadePadrao, double multiplicadorTarifa) {
        this.capacidadePadrao = capacidadePadrao;
        this.multiplicadorTarifa = multiplicadorTarifa;
    }

}