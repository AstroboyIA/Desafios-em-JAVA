package desafio20.src.pricing;

public class PrecoComDesconto implements Precificavel{
    
    private final double percentualDesconto;

    public PrecoComDesconto(double percentualDesconto) {

        if (percentualDesconto < 0.0 || percentualDesconto > 1.0) {
            throw new IllegalArgumentException("O percentual de desconto deve ficar entre 0.0 e 1.0. Valor recebido: " + percentualDesconto);
        }

        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public double calcularPrecoFinal(double preceBase) {
        return preceBase * (1 - percentualDesconto);
    }

    @Override
    public String descricaoEstrategia() {
        return String.format("O desconto  de %.0f%% sobre o preco base", percentualDesconto * 100);
    }
}