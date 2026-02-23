package desafio16.model;

import java.util.ArrayList;
import java.util.List;

public class ChamadoSuporte {
    private String protocolo;
    private String cliente;
    private int slaMinutos;
    
    public String getProtocolo() {
        return protocolo;
    }

    public String getCliente() {
        return cliente;
    }

    public int getSlaMinutos() {
        return slaMinutos;
    }

    public ChamadoSuporte(String protocolo, String cliente, int slaMinutos) {

        if (protocolo == null  || protocolo.isEmpty()) {
            System.out.println("Prococolo não pode ser nulo.");
            throw new IllegalArgumentException();
        }

        if (cliente == null || cliente.isEmpty()) {
            System.out.println("Cliente não pode ser nulo.");
            throw new IllegalArgumentException();
        }

        if (slaMinutos <= 0) {
            System.out.println("O SLA deve ser maior que zero.");
            throw new IllegalArgumentException();
        }

        this.protocolo = protocolo;
        this.cliente = cliente;
        this.slaMinutos = slaMinutos;

    }

    private List<AtividadeAtendimento> atividades = new ArrayList<>();

    public void adicionarAtividade(AtividadeAtendimento atividade) {

        if (atividade == null) {
            System.out.println("A atividade não pode ser nula.");
            throw new IllegalArgumentException();
        }

        if (getStatus() == StatusChamado.RESOLVIDO) {
            System.out.println("Não é possivel adicionar atividade após o chamado ser resolvido!");
            throw new IllegalStateException();
        }

        atividades.add(atividade);
    }

    public int calcularProgressoAtendimento() {
        int totalExecutado = 0;
        int totalEstimado = 0;

        for (AtividadeAtendimento atividade : atividades) {
            totalExecutado += atividade.getMinutosExecutados();
            totalEstimado += atividade.getMinutosEstimados();
        }

        if (totalEstimado == 0)
            return 0;

        if (((totalExecutado * 100) / totalEstimado) > 100)
            return 100;

        return (totalExecutado * 100) / totalEstimado;
    }

    public int calcularPrevisaoTotalMinutos() {
        int minutosEstimados = 0;

        if (atividades.isEmpty())
            return 0;

        for (AtividadeAtendimento atividade : atividades) {
            minutosEstimados += atividade.getMinutosEstimados();
        }

        return minutosEstimados;
    }

    public int calcularTempoExecutadoTotal() {

        int minutosExecutados = 0;

        if (atividades.isEmpty())
            return 0;

        for (AtividadeAtendimento atividade : atividades) {
            minutosExecutados += atividade.getMinutosExecutados();
        }

        return minutosExecutados;
    }

    public int calcularIndiceConsumoSLA() {

        if (slaMinutos == 0)
            return 0;

        int calcularSLA = (calcularTempoExecutadoTotal() * 100) / slaMinutos;

        if (calcularSLA > 100)
            return 100;

        return calcularSLA;
    }

    public StatusChamado getStatus() {

        if (atividades.isEmpty())
            return StatusChamado.ABERTO;

        if (!atividades.isEmpty() && calcularTempoExecutadoTotal() == 0)
            return StatusChamado.ABERTO;

        if (calcularPrevisaoTotalMinutos() == calcularTempoExecutadoTotal())
            return StatusChamado.RESOLVIDO;

        return StatusChamado.EM_ATENDIMENTO;
    }

    public SituacaoSLA getSituacaoSLA() {

        if (calcularPrevisaoTotalMinutos() < slaMinutos)
            return SituacaoSLA.DENTRO_DO_SLA;

        if (calcularPrevisaoTotalMinutos() == slaMinutos)
            return SituacaoSLA.NO_LIMITE;

        return SituacaoSLA.ESTOURADO;
    }

    public RiscoAtendimento getRiscoAtendimento() {

        if (getSituacaoSLA() == SituacaoSLA.DENTRO_DO_SLA)
            return RiscoAtendimento.BAIXO;

        if (getSituacaoSLA() == SituacaoSLA.NO_LIMITE)
            return RiscoAtendimento.MEDIO;

        return RiscoAtendimento.ALTO;
    }
}
