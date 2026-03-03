package desafio19.model.enums;

public enum CanalTransacao {
    
    PRESENCIAL(1),
    ONLINE(2),
    APP(2),
    INTERNACIONAL_ONLINE(3);

    private int pesoRiscoCanal;

    private CanalTransacao(int pesoRiscoCanal) {
        this.pesoRiscoCanal = pesoRiscoCanal;
    }

    public int getPesoRiscoCanal() {
        return pesoRiscoCanal;
    }

    public static CanalTransacao fromOpcao(int opcao) {
        return switch (opcao) {
            case 1 -> PRESENCIAL;
            case 2 -> ONLINE;
            case 3 -> APP;
            case 4 -> INTERNACIONAL_ONLINE;
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }
}
