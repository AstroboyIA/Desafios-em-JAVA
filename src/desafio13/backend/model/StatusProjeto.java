package desafio13.backend.model;

public enum StatusProjeto {
    NAO_INICIADO("Não iniciado"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluido");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private StatusProjeto(String descricao) {
        this.descricao = descricao;
    }
}