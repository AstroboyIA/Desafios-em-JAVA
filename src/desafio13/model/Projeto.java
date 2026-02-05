package desafio13.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String nome;

    public Projeto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public StatusProjeto calcularStatus() {

        if (tarefas.isEmpty()) {
            return StatusProjeto.NAO_INICIARO;
        }

        for (Tarefa tarefa : tarefas) {

            if (!tarefa.isConcluida()) {
                return StatusProjeto.EM_ANDAMENTO;
            }
        }

        return StatusProjeto.CONCLUIDO;

    }
}
