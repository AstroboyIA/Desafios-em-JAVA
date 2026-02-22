package desafio17.model;

public enum SituacaoJanela {
    DENTRO_DA_JANELA("Dentro da janela de mudança"),
    NO_LIMITE("No limite da janela de mudança"),
    ESTOURADO("Fora da janela de mudança");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private SituacaoJanela(String descricao) {
        this.descricao = descricao;
    }
}
