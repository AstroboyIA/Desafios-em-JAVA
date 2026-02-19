package main.desafio16.model;

import java.util.ArrayList;
import java.util.List;

public class ChamadoSuporte {
    private String protocolo;
    private String cliente;
    private int slaMinutos;

    public ChamadoSuporte(String protocolo, String cliente, int slaMinutos) {
        this.protocolo = protocolo;
        this.cliente = cliente;
        this.slaMinutos = slaMinutos;

        if (protocolo.isEmpty() || protocolo == "") {
            System.out.println("Prococolo não pode ser nulo.");
            throw new IllegalArgumentException();
        }

        if (cliente.isEmpty() || cliente == "") {
            System.out.println("Cliente não pode ser nulo.");
            throw new IllegalArgumentException();
        }

        if (slaMinutos < 0) {
            System.out.println("O SLA não pode ser menor que 0.");
        }
    }

    private List<AtividadeAtendimento> atividades = new ArrayList<>();

    public void adicionarAtividade(AtividadeAtendimento atividade) {
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

        int calcularSLA = (calcularTempoExecutadoTotal() * 100) / slaMinutos;
        
        if (slaMinutos == 0)
            return 0;

        if (calcularSLA > 100)
            return 100;

        return calcularSLA;
    }
}
