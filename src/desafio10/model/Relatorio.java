package desafio10.model;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    

    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public void adicionarAvaliacao(Avaliacao avaliacao){
        avaliacoes.add(avaliacao);
    }

    public double calcularMedia(){
        double media = 0.0;
        int tamanho = avaliacoes.size();
        for(Avaliacao avaliacao : avaliacoes){
            media += avaliacao.getNota();
        }
        media = media / tamanho;

        return media;
    }

    public boolean isDesemprenhoSatisfatorio(){
        if (calcularMedia() >= 7.0) {
            return true;
        }else{
            return false;
        }
    }
}