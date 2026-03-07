package desaio20.src.pricing;

public class PrecoNormal implements Precificavel{
    
    private final double precoNormal;

    public PrecoNormal(double precoNormal) {
        this.precoNormal = precoNormal;
    }

    @Override
    public double calcularPrecoFinal(double precoBase) {
        return precoBase;
    }

    public String descricaoEstrategia() {
        return String.format("Preço base do produto: ", precoNormal);
    }
}
