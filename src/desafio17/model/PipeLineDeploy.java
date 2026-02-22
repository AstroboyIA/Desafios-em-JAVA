package desafio17.model;

import java.util.ArrayList;
import java.util.List;

public class PipeLineDeploy {

    private final String idExecucao;
    private final String sistema;
    private final int janelaMudancaMinutos;

    public PipeLineDeploy(String idExecucao, String sistema, int janelaMudancaMinutos) {

        if (idExecucao == null || idExecucao.isEmpty()) {
            System.out.println("O ID não pode ser nulo.");
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        if (sistema == null || sistema.isEmpty()) {
            System.out.println("O sistema não pode ser nulo.");
            throw new IllegalArgumentException("O sistema não pode ser nulo.");
        }

        if (janelaMudancaMinutos <= 0) {
            System.out.println("A janela de mudança deve ser maior que zero.");
            throw new IllegalArgumentException("A janela de mudança deve ser maior que zero.");
        }

        this.idExecucao = idExecucao;
        this.sistema = sistema;
        this.janelaMudancaMinutos = janelaMudancaMinutos;
    }

    private final List<EtapaDeploy> etapas = new ArrayList<>();

    public void adicionarEtapa(EtapaDeploy etapa) {

        if (etapa == null) {
            throw new IllegalArgumentException("A etapa não pode ser nula.");
        }

        if (condition) {
            throw new IllegalStateException("Não é permitido adicionar etapa após o pipeline estar finalizado.");
        }

        etapas.add(etapa);
    }

    public StatusPipeLine getStatus() {

        if (etapas.isEmpty()) {
            return StatusPipeLine.ABERTO;
        } else if (calcularTempoExecutadoPonderado() == 0) {
            return StatusPipeLine.ABERTO;
        } else if (calcularTempoExecutadoPonderado() == calcularPrevisaoTotalPonderada()) {
            return StatusPipeLine.CONCLUIDO;
        } else {
            return StatusPipeLine.EM_EXECUCAO;
        }
    }

    public int calcularPrevisaoTotalPonderada() {

        int totalPonderado = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaDeploy etapa : etapas) {
            totalPonderado += etapa.getMinutosEstimados();
        }

        return totalPonderado * criticidade.getPeso();
    }

    public int calcularTempoExecutadoPonderado() {

        int totalExecutado = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaDeploy etapa : etapas) {
            totalExecutado += etapa.getMinutosExecutados();
        }

        return totalExecutado * criticidade.getPeso();
    }

    public int calcularProgressoDeploy() {

        int progresso = 0;

        if (calcularPrevisaoTotalPonderada() == 0)
            return 0;

        progresso = (calcularTempoExecutadoPonderado() * 100) / calcularPrevisaoTotalPonderada();

        if (progresso > 100)
            progresso = 100;

        return progresso;
    }

    public SituacaoJanela getSituacaoJanela() {

        if (calcularPrevisaoTotalPonderada() < janelaMudancaMinutos) {
            return SituacaoJanela.DENTRO_DA_JANELA;
        } else if (calcularPrevisaoTotalPonderada() == janelaMudancaMinutos) {
            return SituacaoJanela.NO_LIMITE;
        } else {
            return SituacaoJanela.ESTOURADO;
        }
    }

    public int calcularIndiceConsumoJanela() {

        int indiceConsumo = 0;

        if (janelaMudancaMinutos == 0)
            return 0;

        indiceConsumo = (calcularTempoExecutadoPonderado() * 100) / janelaMudancaMinutos;

        if (indiceConsumo > 100)
            indiceConsumo = 100;

        return indiceConsumo;
    }

    public RiscoOperacionalDeploy getRiscoOperacional() {

        if (getSituacaoJanela() == SituacaoJanela.DENTRO_DA_JANELA) {
            return RiscoOperacionalDeploy.BAIXO;
        } else if (getSituacaoJanela() == SituacaoJanela.NO_LIMITE) {
            return RiscoOperacionalDeploy.MEDIO;
        } else {
            return RiscoOperacionalDeploy.ALTO;
        }
    }
}
