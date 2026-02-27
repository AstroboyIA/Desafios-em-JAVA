package desafio18.model;

public class AcaoResposta {
    
    private final String descricao;
    private final int minutosPlanejados;
    private int minutosExecutados;
    private final TipoAcao tipo;


    public String getDescricao() {
        return descricao;
    }


    public int getMinutosPlanejados() {
        return minutosPlanejados;
    }


    public int getMinutosExecutados() {
        return minutosExecutados;
    }


    public TipoAcao getTipo() {
        return tipo;
    }


    public AcaoResposta(String descricao, int minutosPlanejados, int minutosExecutados, TipoAcao tipo) {
        
        if (descricao == null || descricao.isEmpty())
            throw new IllegalArgumentException("Descrição da ação de resposta não pode estar vazia.");

        if (minutosPlanejados <= 0)
            throw new IllegalArgumentException("Minutos planejados precisa ser maior que 0.");

        if (minutosExecutados < 0)
            throw new IllegalArgumentException("Minutos executados não pode ser negativo.");

        if (minutosExecutados > minutosPlanejados)
            throw new IllegalArgumentException("Tempo de execução deve ser menor que os minutos planejados.");

        if (tipo == null)
            throw new IllegalArgumentException("Tipo da ação de resposta deve ser informado.");

        this.descricao = descricao;
        this.minutosPlanejados = minutosPlanejados;
        this.minutosExecutados = minutosExecutados;
        this.tipo = tipo;
    }
}
