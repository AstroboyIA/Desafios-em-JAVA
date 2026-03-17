package desafio21.src.domain;

public class ServicoRefeicao {
    
    private final double valor;
    private final String tipoDieta;
    private final boolean incluidoNoPacote;

    public double getValor() {
        return valor;
    }

    public String getTipoDieta() {
        return tipoDieta;
    }

    public boolean isIncluidoNoPacote() {
        return incluidoNoPacote;
    }

    public ServicoRefeicao(double valor, String tipoDieta, boolean incluidoNoPacote) {
        this.valor = valor;
        this.tipoDieta = tipoDieta;
        this.incluidoNoPacote = incluidoNoPacote;
    }

}
