package desaio20.src.pricing;

public class PrecoComMarkup implements Precificavel{
    
    private final double percentualMarkup;

    public PrecoComMarkup(double percentualMarkup) {
        this.percentualMarkup = percentualMarkup;
    }

    @Override
    public double calcularPrecoFinal(double precoBase) {
        return precoBase * (1 + percentualMarkup);
    }

    @Override
    public String descricaoEstrategia() {
        return String.format("O acréscimo de %.0f%% sobre o preco base", percentualMarkup);
    }
}
