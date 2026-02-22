package desafio17.model;

public enum StatusPipeLine {
    ABERTO("Aberto"),
    EM_EXECUCAO("Em execução"),
    CONCLUIDO("Concluído");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private StatusPipeLine(String descricao) {
        this.descricao = descricao;
    }
}


