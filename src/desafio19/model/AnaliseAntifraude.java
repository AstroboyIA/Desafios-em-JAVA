package desafio19.model;

import desafio19.model.enums.NivelFraude;
import desafio19.model.enums.StatusAutorizacao;

public class AnaliseAntifraude {
     
    private final int score;
    private final NivelFraude nivel;
    private final StatusAutorizacao status;
    private final double valor;
    
    public int getScore() {
        return score;
    }

    public NivelFraude getNivel() {
        return nivel;
    }

    public StatusAutorizacao getStatus() {
        return status;
    }

    public double getValor() {
        return valor;
    }

    public AnaliseAntifraude(int score, NivelFraude nivel, StatusAutorizacao status, double valor) {
        this.score = score;
        this.nivel = nivel;
        this.status = status;
        this.valor = valor;
    }

}
