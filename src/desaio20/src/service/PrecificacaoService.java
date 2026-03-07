package desaio20.src.service;

import desaio20.src.pricing.Precificavel;

public class PrecificacaoService {
    
    private Precificavel estrategiaInicial;

    public PrecificacaoService(Precificavel estrategiaInicial) {
        this.estrategiaInicial = estrategiaInicial;
    }

    public void alterarEstrategia (Precificavel novaEstrategia) {}

    public void calcularPreco(double precoBase) {}

    public void descricaoEstrategiaAtual() {}
    
}
