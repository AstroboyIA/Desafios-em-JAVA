package desafio15.model;

public class EtapaProducao {
    String descricaoEtapa;
    int tempoEstimadoMinutos;
    int tempoExecutadoMinutos;
    
    public int getTempoEstimadoMinutos() {
        return tempoEstimadoMinutos;
    }

    public int getTempoExecutadoMinutos() {
        return tempoExecutadoMinutos;
    }

    public EtapaProducao(String descricaoEtapa, int tempoEstimadoMinutos, int tempoExecutadoMinutos) {
        this.descricaoEtapa = descricaoEtapa;
        this.tempoEstimadoMinutos = tempoEstimadoMinutos;
        this.tempoExecutadoMinutos = tempoExecutadoMinutos;

        if (descricaoEtapa.isEmpty() || descricaoEtapa == "") {
            System.out.println("A descrição não pode ficar vazia.");
            throw new IllegalArgumentException();
        }

        if (tempoEstimadoMinutos < 0) {
            System.out.println("Tempo estimado não pode ser negativo.");
            throw new IllegalArgumentException();
        }

        if (tempoExecutadoMinutos < 0) {
            System.out.println("Tempo executado não pode ser negativo.");
            throw new IllegalArgumentException();
        }

        if (tempoExecutadoMinutos > tempoEstimadoMinutos) {
            System.out.println("O tempo exetudado não pode ser maior que o tempo estimado.");
            throw new IllegalArgumentException();
        }
    }

    public boolean isFinalizado() {
        return tempoExecutadoMinutos >= tempoEstimadoMinutos;
    }
}
