package desafio10.model;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public double calcularMedia() {
    
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
                soma += avaliacao.getNota();
            }
            return soma / avaliacoes.size();
        }

    public boolean isDesempenhoSatisfatorio() {
        return calcularMedia() >= 7.0;
    }
}