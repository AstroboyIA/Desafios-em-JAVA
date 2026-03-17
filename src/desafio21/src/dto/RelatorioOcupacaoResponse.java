package desafio21.src.dto;

import java.util.List;
import java.util.Map;

import desafio10.model.Relatorio;
import desafio21.src.domain.enums.TipoQuarto;

public class RelatorioOcupacaoResponse {

    private final int totalQuartos;
    private final int quartosOcupados;
    private final int quartosDisponiveis;
    private final double taxaOcupacao; // % de ocupação
    private final double receitaTotal; // soma de todas as reservas ATIVAS
    private final Map<TipoQuarto, Integer> reservasPorTipo;
    private final List<String> alertasServicos;

    public int getTotalQuartos() {
        return totalQuartos;
    }

    public int getQuartosOcupados() {
        return quartosOcupados;
    }

    public int getQuartosDisponiveis() {
        return quartosDisponiveis;
    }

    public double getTaxaOcupacao() {
        return taxaOcupacao;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public Map<TipoQuarto, Integer> getReservasPorTipo() {
        return reservasPorTipo;
    }

    public List<String> getAlertasServicos() {
        return alertasServicos;
    }

    public RelatorioOcupacaoResponse(Builder builder) {

        this.totalQuartos = builder.totalQuartos;
        this.quartosOcupados = builder.quartosOcupados;
        this.quartosDisponiveis = builder.quartosDisponiveis;
        this.taxaOcupacao = builder.taxaOcupacao;
        this.receitaTotal = builder.receitaTotal;
        this.reservasPorTipo = builder.reservasPorTipo;
        this.alertasServicos = builder.alertasServicos;

    }

    public static class Builder {

        private int totalQuartos;
        private int quartosOcupados;
        private int quartosDisponiveis;
        private double taxaOcupacao;
        private double receitaTotal;
        private Map<TipoQuarto, Integer> reservasPorTipo;
        private List<String> alertasServicos;

        public Builder totalQuartos(int val) { this.totalQuartos = val; return this; }
        public Builder quartosOcupados(int val) { this.quartosOcupados = val; return this; }
        public Builder quartosDisponiveis(int val) { this.quartosDisponiveis = val; return this; }
        public Builder taxaOcupacao(double val) { this.taxaOcupacao = val; return this; }
        public Builder receitaTotal(double val) { this.receitaTotal = val; return this;}
        public Builder revervasPorTipo(Map<TipoQuarto,Integer> val) { this.reservasPorTipo = val; return this;}
        public Builder alertasServicos(List<String> val) { this.alertasServicos = val; return this;}

        public RelatorioOcupacaoResponse build() {
            return new RelatorioOcupacaoResponse(this);
        }
    }

}
