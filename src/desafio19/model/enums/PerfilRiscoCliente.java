package desafio19.model.enums;

public enum PerfilRiscoCliente {
    
    BAIXO(1),
    MODERADO(2),
    ALTO(3);

    private int multiplicadorRisco;

    public int getMultiplicadorRisco() {
        return multiplicadorRisco;
    }

    private PerfilRiscoCliente(int multiplicadorRisco) {
        this.multiplicadorRisco = multiplicadorRisco;
    }

    public static PerfilRiscoCliente fromOpcao(int opcao) {
        return switch (opcao) {
            case 1 -> BAIXO;
            case 2 -> MODERADO;
            case 3 -> ALTO;
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }
}
