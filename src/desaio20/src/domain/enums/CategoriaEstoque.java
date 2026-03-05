package desaio20.src.domain.enums;

public enum CategoriaEstoque {
    ELETRONICO(5),
    ALIMENTACAO(20),
    VESTUARIO(10),
    DIGITAL(0),
    PERECIVEL(15);

    public int quantidadeMinimaRecomendada;

    private CategoriaEstoque(int quantidadeMinimaRecomendada) {
        this.quantidadeMinimaRecomendada = quantidadeMinimaRecomendada;
    }

    public int getQuantidadeMinimaRecomendada() {
        return quantidadeMinimaRecomendada;
    }

}
