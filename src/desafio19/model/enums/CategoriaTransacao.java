package desafio19.model.enums;

public enum CategoriaTransacao {
    
    ALIMENTACAO(1),
    ELETRONICOS(2),
    JOIAS(4),
    SERVICOS(1),
    INTERNACIONAL(5);

    private int pesoRisco;

    public int getPesoRisco() {
        return pesoRisco;
    }

    private CategoriaTransacao(int pesoRisco) {
        this.pesoRisco = pesoRisco;
    }

    public static CategoriaTransacao fromOpcao(int opcao) {
        return switch (opcao) {
            case 1 -> ALIMENTACAO;
            case 2 -> ELETRONICOS;
            case 3 -> JOIAS;
            case 4 -> SERVICOS;
            case 5 -> INTERNACIONAL;
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }
}
