package desafio13.model;

public enum StatusProjeto {
    NAO_INICIARO("Não iniciado"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluido");

    private StatusProjeto(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }
}