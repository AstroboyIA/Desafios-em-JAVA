package desafio20.src.pricing;

public class PrecoNormal implements Precificavel{
    
    @Override
    public double calcularPrecoFinal(double precoBase) {
        return precoBase;
    }

    @Override
    public String descricaoEstrategia() {
        return "Preço normal, sem alteração.";
    }
}
