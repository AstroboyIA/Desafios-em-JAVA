package desafio17.model;

public enum StatusPipeline {
    ABERTO("Aberto"),
    EM_EXECUCAO("Em execução"),
    CONCLUIDO("Concluído");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private StatusPipeline(String descricao) {
        this.descricao = descricao;
    }
}


