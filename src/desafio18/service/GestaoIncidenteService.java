package desafio18.service;

import java.util.Scanner;

import desafio18.model.AcaoResposta;
import desafio18.model.IncidenteOperacional;
import desafio18.model.SeveridadeIncidente;
import desafio18.model.TipoAcao;

public class GestaoIncidenteService {

    public void Welcome() {
        System.out.println("Bem-vindo ao sistema de gestão de incidentes!");
    }

    public IncidenteOperacional criarIncidente(Scanner sc) {

        String codigo;
        String servicoAfetado;
        SeveridadeIncidente severidade = null;
        int tempoSlaMinutos;
        int tempoDecorridoMinutos;
        byte opcaoSeveridade;
        boolean opcaoValida = false;

        System.out.println("Para reportar um incidente, por favor, forneça as seguintes informações:");
        System.out.print("Código do incidente: ");
        codigo = sc.nextLine();
        System.out.print("Serviço afetado: ");
        servicoAfetado = sc.nextLine();
        System.out.print(
                "Severidade do incidente: \n1 - Baixa \n2 - Média \n3 - Alta \n4 - Crítica)\nEscolha uma opção: ");
        opcaoSeveridade = sc.nextByte();
        sc.nextLine();

        while (opcaoValida == false) {

            switch (opcaoSeveridade) {
                case 1:
                    severidade = SeveridadeIncidente.BAIXA;
                    opcaoValida = true;
                    break;
                case 2:
                    severidade = SeveridadeIncidente.MODERADA;
                    opcaoValida = true;
                    break;
                case 3:
                    severidade = SeveridadeIncidente.ALTA;
                    opcaoValida = true;
                    break;
                case 4:
                    severidade = SeveridadeIncidente.CRITICA;
                    opcaoValida = true;
                    break;
                default:
                    System.out.print("Opção inválida. Por favor, escolha uma opção válida!");
            }
        }

        System.out.print("Tempo do SLA (em minutos): ");
        tempoSlaMinutos = sc.nextInt();
        sc.nextLine();
        System.out.print("Tempo decorrido desde a abertura do incidente (em minutos): ");
        tempoDecorridoMinutos = sc.nextInt();
        sc.nextLine();

        IncidenteOperacional incidente = new IncidenteOperacional(codigo, servicoAfetado, severidade, tempoSlaMinutos,
                tempoDecorridoMinutos);

        return incidente;

    }

    public AcaoResposta adicionarAcaoResposta(Scanner sc, IncidenteOperacional incidente) {

        String descricao;
        int minutosPlanejados;
        int minutosExecutados;
        TipoAcao tipo = null;
        byte opcaoTipo;
        boolean opcaoValida = false;

        System.out.println(
                "Para adicionar uma ação de resposta ao seu incidente, por favor, forneça as seguintes informações:");
        System.out.println("Descrição da ação de resposta: ");
        descricao = sc.nextLine();
        System.out.println("Minutos planejados para execução da ação: ");
        minutosPlanejados = sc.nextInt();
        sc.nextLine();
        System.out.println("Minutos executados para a ação: ");
        minutosExecutados = sc.nextInt();
        sc.nextLine();
        System.out.println(
                "Tipo da ação de resposta: \n1 - Contenção \n2 - Mitigação \n3 - Correção \n4 - Comunicação \nEscolha uma opção: ");
        opcaoTipo = sc.nextByte();
        sc.nextLine();

        while (opcaoValida == false) {

            switch (opcaoTipo) {
                case 1:
                    tipo = TipoAcao.CONTENCAO;
                    opcaoValida = true;
                    break;
                case 2:
                    tipo = TipoAcao.MITIGACAO;
                    opcaoValida = true;
                    break;
                case 3:
                    tipo = TipoAcao.CORRECAO;
                    opcaoValida = true;
                    break;
                case 4:
                    tipo = TipoAcao.COMUNICACAO;
                    opcaoValida = true;
                    break;
                default:
                    System.out.print("Opção inválida. Por favor, escolha uma opção válida!");
            }
        }

        AcaoResposta acaoResposta = new AcaoResposta(descricao, minutosPlanejados, minutosExecutados, tipo);

        incidente.adicionarAcoes(acaoResposta);

        return acaoResposta;

    }

    public void resumoFinal(IncidenteOperacional incidente) {
        System.out.println("\nResumo final do incidente e ações de resposta:");
        System.out.println("\nIncidente: "+incidente.getCodigo());
        System.out.println("Serviço afetado: "+incidente.getServicoAfetado());
        System.out.println("Severidade: "+incidente.getSeveridade().getDescricao());
        System.out.println("Status: "+incidente.getStatus().getDescricao());
        System.out.println("Índice de execução: "+incidente.calcularIndiceExecucao()+"%");
        System.out.println("Saturação operacional: "+incidente.calcularSaturacao()+"%");
        System.out.println("Penalidade acumulada: "+incidente.calcularPenalidade());
        System.out.println("Nível de risco: "+incidente.getNivelRisco().getDescricao());
        System.out.println("Índice de eficiencia: "+incidente.calcularIndiceEficiencia());
    }
}