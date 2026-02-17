package desafio15.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoProducao {

    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    private int tempoMetaMinutos;

    public int getTempoMetaMinutos() {
        return tempoMetaMinutos;
    }

    public PedidoProducao(String nomeCliente, int tempoMetaMinutos) {
        this.nomeCliente = nomeCliente;
        this.tempoMetaMinutos = tempoMetaMinutos;
    }

    private List<EtapaProducao> etapas = new ArrayList<>();

    public void adicionarEtapa(EtapaProducao etapa) {
        etapas.add(etapa);
    }

    public StatusPedido calcularStatus() {

        if (etapas.isEmpty()) {
            return StatusPedido.NAO_INICIADO;
        }else if (!etapas.isEmpty() && calcularTempoExecutadoTotal() == 0) {
            return StatusPedido.NAO_INICIADO;
        }else if (calcularTempoExecutadoTotal() == calcularPrevisaoTotalMinutos()) {
            return StatusPedido.FINALIZADO;
        }else {
            return StatusPedido.EM_PREPARO;
        }
    }

    public int calcularPrevisaoTotalMinutos() {

        int previsaoTotal = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaProducao etapa : etapas) {
            previsaoTotal += etapa.getTempoEstimadoMinutos();
        }

        return previsaoTotal;
    }

    public int calcularTempoExecutadoTotal() {

        int totalExecutado = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaProducao etapa : etapas) {
            totalExecutado += etapa.getTempoExecutadoMinutos();
        }
        return totalExecutado;
    }

    public int calcularProgressoExecucao() {

        int totalExecutadoEtapas = 0;
        int totalEstimadoEtapas = 0;

        for (EtapaProducao etapa : etapas) {
            totalEstimadoEtapas += etapa.getTempoEstimadoMinutos();
            totalExecutadoEtapas += etapa.getTempoExecutadoMinutos();
        }

        if (totalEstimadoEtapas == 0)
            return 0;

        return (totalExecutadoEtapas * 100) / totalEstimadoEtapas;
    }

    public SituacaoPrazoPedido calcularSituacaoPrazo() {

        int previsaoTotal = calcularPrevisaoTotalMinutos();

        if (previsaoTotal < tempoMetaMinutos) {
            return SituacaoPrazoPedido.ADIANTADO;
        } else if (previsaoTotal == tempoMetaMinutos) {
            return SituacaoPrazoPedido.NO_PRAZO;
        }else {
            return SituacaoPrazoPedido.ATRASADO;
        }
    }

    public int calcularIndiceCumprimentoMeta() {

        int tempoExecutadoTotal = 0;

        if (tempoMetaMinutos == 0)
            return 0;

        for (EtapaProducao etapa : etapas) {
            tempoExecutadoTotal += etapa.getTempoExecutadoMinutos();
        }

        if ((tempoExecutadoTotal * 100) / tempoMetaMinutos > 100) {
            return 100;
        }

        return (tempoExecutadoTotal * 100) / tempoMetaMinutos;
    }

    public RiscoOperacional calcularRisco() {
        
        if (calcularSituacaoPrazo() == SituacaoPrazoPedido.ATRASADO) {
            return RiscoOperacional.ALTO;
        }else if (calcularSituacaoPrazo() == SituacaoPrazoPedido.NO_PRAZO) {
            return RiscoOperacional.MEDIO;
        }else {
            return RiscoOperacional.BAIXO;
        }
    }
}
