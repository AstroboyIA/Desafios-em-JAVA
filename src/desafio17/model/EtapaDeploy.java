package desafio17.model;

public class EtapaDeploy {

    private final String nome;
    private final int minutosEstimados;
    private final int minutosExecutados;
    private final CriticidadeEtapa criticidade;

    public String getNome() {
        return nome;
    }

    public int getMinutosEstimados() {
        return minutosEstimados;
    }

    public int getMinutosExecutados() {
        return minutosExecutados;
    }

    public EtapaDeploy(String nome, int minutosEstimados, int minutosExecutados, CriticidadeEtapa criticidade) {
        
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome da etapa não pode ser nulo.");
        }

        if (minutosEstimados <= 0) {
            throw new IllegalArgumentException("Os minutos estimados devem ser maiores que zero.");
        }

        if (minutosExecutados < 0) {
            throw new IllegalArgumentException("Os minutos executados não podem ser negativos.");
        }

        if (minutosExecutados > minutosEstimados) {
            throw new IllegalArgumentException("Os minutos executados não podem ser maiores que os minutos estimados.");
        }

        if (criticidade == null) {
            throw new IllegalArgumentException("A criticidade da etapa não pode ser nula.");
        }
        
        this.nome = nome;
        this.minutosEstimados = minutosEstimados;
        this.minutosExecutados = minutosExecutados;
        this.criticidade = criticidade;
    }
}
