package desafio19.model.enums;

public enum StatusAutorizacao {
    
    APROVADA("Aprovada"),
    NEGADA("Negada"),
    BLOQUEADA("Bloqueada");

    private String statusAutorizacao;

    private StatusAutorizacao(String statusAutorizacao) {
        this.statusAutorizacao = statusAutorizacao;
    }

    public String getStatusAutorizacao() {
        return statusAutorizacao;
    }

}
