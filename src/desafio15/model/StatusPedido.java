package main.desafio15.model;

public enum StatusPedido {
    NAO_INICIADO("Não iniciado"),
    EM_PREPARO("Em preparo"),
    FINALIZADO("Finalizado");

    private String Status;

    private StatusPedido(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }
}
