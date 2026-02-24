package desafio18.model;

public enum StatusIncidente {
    
    ABERTO("Incidente aberto"),
    EM_TRATAMENTO("Incidente em tratamento"),
    RESOLVIDO("Incidente resolvido"),
    ENCERRADO("Incidente encerrado");

    private final String descricao;

    public String getDescricao() {
        return descricao;
    }

    StatusIncidente(String descricao) {
        this.descricao = descricao;
    }
}
