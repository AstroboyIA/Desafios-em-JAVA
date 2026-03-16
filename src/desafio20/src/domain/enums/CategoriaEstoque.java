package desafio20.src.domain.enums;

public enum CategoriaEstoque {
    ELETRONICO(5),
    ALIMENTICIO(20),
    VESTUARIO(10),
    DIGITAL(0),
    PERECIVEL(15);

    private final int quantidadeMinimaRecomendada;

    private CategoriaEstoque(int quantidadeMinimaRecomendada) {
        this.quantidadeMinimaRecomendada = quantidadeMinimaRecomendada;
    }

    public int getQuantidadeMinimaRecomendada() {
        return quantidadeMinimaRecomendada;
    }

}