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
}
