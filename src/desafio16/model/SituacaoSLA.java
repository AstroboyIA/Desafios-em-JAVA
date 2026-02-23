package desafio16.model;

public enum SituacaoSLA {
    DENTRO_DO_SLA("Dentro do SLA"),
    NO_LIMITE("No limíte"),
    ESTOURADO("Estourado");

    private String descricao;

    private SituacaoSLA(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
