package desafio17.model;

import java.util.ArrayList;
import java.util.List;

public class PipelineDeploy {

    private final String idExecucao;
    private final String sistema;
    private final int janelaMudancaMinutos;

    public String getIdExecucao() {
        return idExecucao;
    }

    public String getSistema() {
        return sistema;
    }

    public int getJanelaMudancaMinutos() {
        return janelaMudancaMinutos;
    }

    public PipelineDeploy(String idExecucao, String sistema, int janelaMudancaMinutos) {

        if (idExecucao == null || idExecucao.isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        if (sistema == null || sistema.isEmpty()) {
            throw new IllegalArgumentException("O sistema não pode ser nulo.");
        }

        if (janelaMudancaMinutos <= 0) {
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

        if (getStatus() == StatusPipeline.CONCLUIDO) {
            throw new IllegalStateException("Não é permitido adicionar etapa após o pipeline estar finalizado.");
        }

        etapas.add(etapa);
    }

    public StatusPipeline getStatus() {

        boolean todasConcluidas = true;

        for (EtapaDeploy etapa : etapas) {
            if (!etapa.etapaIsConcluida()) {
                todasConcluidas = false;
                break;
            }
        }

        if (etapas.isEmpty()) {
            return StatusPipeline.ABERTO;
        } else if (calcularTempoExecutadoPonderado() == 0) {
            return StatusPipeline.ABERTO;
        } else if (todasConcluidas) {
            return StatusPipeline.CONCLUIDO;
        } else {
            return StatusPipeline.EM_EXECUCAO;
        }
    }

    public int calcularPrevisaoTotalPonderada() {

        int totalPonderado = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaDeploy etapa : etapas) {
            totalPonderado += etapa.getMinutosEstimados() * etapa.getCriticidade().getPeso();
        }

        return totalPonderado;
    }

    public int calcularTempoExecutadoPonderado() {

        int totalExecutado = 0;

        if (etapas.isEmpty())
            return 0;

        for (EtapaDeploy etapa : etapas) {
            totalExecutado += etapa.getMinutosExecutados() * etapa.getCriticidade().getPeso();
        }

        return totalExecutado;
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

        RiscoOperacionalDeploy risco;

        if (getSituacaoJanela() == SituacaoJanela.DENTRO_DA_JANELA) {
            risco = RiscoOperacionalDeploy.BAIXO;
        } else if (getSituacaoJanela() == SituacaoJanela.NO_LIMITE) {
            risco = RiscoOperacionalDeploy.MEDIO;
        } else {
            risco = RiscoOperacionalDeploy.ALTO;
        }

        boolean existeAltaPendente = false;

        for (EtapaDeploy etapa : etapas) {
            if (etapa.getCriticidade() == CriticidadeEtapa.ALTA && !etapa.etapaIsConcluida()) {
                existeAltaPendente = true;
                break;
            }
        }
        if (existeAltaPendente && calcularIndiceConsumoJanela() >= 80) {

            if (risco == RiscoOperacionalDeploy.BAIXO) {
                risco = RiscoOperacionalDeploy.MEDIO;
            } else if (risco == RiscoOperacionalDeploy.MEDIO) {
                risco = RiscoOperacionalDeploy.ALTO;
            }
        }

        return risco;
    }
}