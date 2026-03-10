package desaio20.src.service;

import desaio20.src.pricing.Precificavel;

public class PrecificacaoService {

    private Precificavel estrategiaAtual;

    public PrecificacaoService(Precificavel estrategiaAtual) {

        if (estrategiaAtual == null) {
            throw new IllegalArgumentException("Por favor, informe a estratégia inicial.");
        }
        this.estrategiaAtual = estrategiaAtual;
    }

    public void alterarEstrategia (Precificavel novaEstrategia) {

        if (novaEstrategia == null) {
            throw new IllegalArgumentException("Por favor, informe a nova estratégia.");
        }

        this.estrategiaAtual = novaEstrategia;
    }

    public double calcularPreco(double precoBase) {

        return estrategiaAtual.calcularPrecoFinal(precoBase);

    }

    public String descricaoEstrategiaAtual() {

        return estrategiaAtual.descricaoEstrategia();

    }

    public Precificavel getEstrategiaAtual() {
        return estrategiaAtual;
    }

}
