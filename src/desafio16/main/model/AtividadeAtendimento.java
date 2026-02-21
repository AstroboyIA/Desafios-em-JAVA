package main.desafio16.model;

public class AtividadeAtendimento {
    private String descricao;
    private int minutosEstimados;
    private int minutosExecutados;

    public String getDescricao() {
        return descricao;
    }

    public int getMinutosEstimados() {
        return minutosEstimados;
    }

    public int getMinutosExecutados() {
        return minutosExecutados;
    }

    public AtividadeAtendimento(String descricao, int minutosEstimados, int minutosExecutados) {

        if (descricao == null || descricao.isEmpty()) {
            System.out.println("Descrição não pode ficar vazia.");
            throw new IllegalArgumentException();
        }

        if (minutosEstimados <= 0) {
            System.out.println("Os minutos estimados, não podem ser negativos.");
            throw new IllegalArgumentException();
        }

        if (minutosExecutados < 0) {
            System.out.println("Os minutos executados, não podem ser negativos.");
            throw new IllegalArgumentException();
        }

        if (minutosExecutados > minutosEstimados) {
            System.out.println("O tempo de execução não pode ser maior que o tempo estimado.");
            throw new IllegalArgumentException();
        }

        this.descricao = descricao;
        this.minutosEstimados = minutosEstimados;
        this.minutosExecutados = minutosExecutados;

    }
}
