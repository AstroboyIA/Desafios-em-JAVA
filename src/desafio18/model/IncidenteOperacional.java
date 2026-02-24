package desafio18.model;

import java.util.ArrayList;
import java.util.List;

public class IncidenteOperacional {

    private final String codigo;
    private final String servicoAfetado;
    private final SeveridadeIncidente severidade;
    private final int tempoSlaMinutos;
    private final int tempoDecorridoMinutos;

    public IncidenteOperacional(String codigo, String servicoAfetado, SeveridadeIncidente severidade,
            int tempoSlaMinutos, int tempoDecorridoMinutos) {

        if (codigo == null || codigo.isEmpty())
            throw new IllegalArgumentException("Código do incidente não pode estar vazio.");

        if (servicoAfetado == null || servicoAfetado.isEmpty())
            throw new IllegalArgumentException("É necessário informar um serviço afetado.");

        if (severidade == null)
            throw new IllegalArgumentException("Severidade do incidente deve ser informada.");

        if (tempoSlaMinutos < 0)
            throw new IllegalArgumentException("O tempo do SLA não pode ser negativo.");

        if (tempoDecorridoMinutos < 0)
            throw new IllegalArgumentException("O tempo decorrido não pode ser negativo.");

        // if ()
        // throw new IllegalStateException("Não é possível adicionar ações após o
        // incidente estar encerrado.");

        this.codigo = codigo;
        this.servicoAfetado = servicoAfetado;
        this.severidade = severidade;
        this.tempoSlaMinutos = tempoSlaMinutos;
        this.tempoDecorridoMinutos = tempoDecorridoMinutos;
    }

    private final List<AcaoResposta> acoes = new ArrayList<>();

    public StatusIncidente getStatus() {

        int minutosExecutados = 0;
        for (AcaoResposta acao : acoes) {
            minutosExecutados += acao.getMinutosExecutados();
        }

        if (acoes.isEmpty())
            return StatusIncidente.ABERTO;

        if (minutosExecutados == 0)
            return StatusIncidente.ABERTO;

        if (minutosExecutados >= tempoSlaMinutos)
            return StatusIncidente.RESOLVIDO;

        return StatusIncidente.EM_TRATAMENTO;
    }

    public int calcularIndiceExecucao() {

        int executadosPonderado = 0;
        int planejadoPoderado = 0;
        int resultado = 0;
        int peso = 0;

        for (AcaoResposta acao : acoes) {
            peso = acao.getTipo().getPesoImpacto();
            executadosPonderado += acao.getMinutosExecutados() * peso;
            planejadoPoderado += acao.getMinutosPlanejados() * peso;
        }

        if (planejadoPoderado == 0) return 0;
        

        resultado = (executadosPonderado * 100) / planejadoPoderado;

        if (resultado > 100) resultado = 100;
        if (resultado < 0) resultado = 0;
    

        return resultado;
    }

    public int calcularSaturacao() {

        int saturacao = (tempoDecorridoMinutos * 100) / tempoSlaMinutos;

        if (saturacao > 200)
            saturacao = 200;

        return saturacao;
    }

    public NivelRiscoIncidente getNivelRisco() {

        int saturacao = calcularSaturacao();
        boolean isAgravante = false;
        NivelRiscoIncidente nivelRisco = null;
        boolean isRedutor = false;
        boolean isConcluida = false;

        for (AcaoResposta acao : acoes) {
            if (acao.getMinutosExecutados() == acao.getMinutosPlanejados()) {
                isConcluida = true;
            }
        }

        if (severidade == SeveridadeIncidente.CRITICA && saturacao >= 90)
            isAgravante = true;

        if (saturacao < 70) {
            nivelRisco = NivelRiscoIncidente.CONTROLADO;
        }

        if (saturacao >= 70 && saturacao <= 99) {
            nivelRisco = NivelRiscoIncidente.ATENCAO;
        }

        if (saturacao >= 100 && saturacao <= 149) {
            nivelRisco = NivelRiscoIncidente.CRITICO;
        }

        if (saturacao >= 150)
            nivelRisco = NivelRiscoIncidente.COLAPSO;

        if (isAgravante) {
            if (nivelRisco == NivelRiscoIncidente.CONTROLADO) {
                nivelRisco = NivelRiscoIncidente.ATENCAO;
            } else if (nivelRisco == NivelRiscoIncidente.ATENCAO) {
                nivelRisco = NivelRiscoIncidente.CRITICO;
            } else if (nivelRisco == NivelRiscoIncidente.CRITICO) {
                nivelRisco = NivelRiscoIncidente.COLAPSO;
            } else if (nivelRisco == NivelRiscoIncidente.COLAPSO) {
                nivelRisco = NivelRiscoIncidente.COLAPSO;
            }
        }

        for (AcaoResposta acao : acoes) {
            if (acao.getTipo() == TipoAcao.CORRECAO && isConcluida == true) {
                isRedutor = true;
                break;
            }
        }

        if (isRedutor) {
            if (nivelRisco == NivelRiscoIncidente.COLAPSO) {
                nivelRisco = NivelRiscoIncidente.CRITICO;
            } else if (nivelRisco == NivelRiscoIncidente.CRITICO) {
                nivelRisco = NivelRiscoIncidente.ATENCAO;
            } else if (nivelRisco == NivelRiscoIncidente.ATENCAO) {
                nivelRisco = NivelRiscoIncidente.CONTROLADO;
            }
        }

        return nivelRisco;
    }

    public int calcularIndiceEficiencia() {

        int eficiencia = 0;

        eficiencia = calcularIndiceExecucao() - calcularSaturacao();

        if (eficiencia < -100)
            eficiencia = -100;

        if (eficiencia > 100)
            eficiencia = 100;

        return eficiencia;
    }
}
