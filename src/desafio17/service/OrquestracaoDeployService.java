package desafio17.service;

import java.util.Scanner;

import desafio17.model.CriticidadeEtapa;
import desafio17.model.EtapaDeploy;
import desafio17.model.PipelineDeploy;

public class OrquestracaoDeployService {

    public void Wellcome() {
        System.out.println("Bem-vindo ao serviço de orquestração de deploy!");
    }

    public PipelineDeploy criarPipeline(Scanner sc) {

        String id;
        String sistema;
        int janelaTempo;
        PipelineDeploy pipelineDeploy;

        System.out.println("Criando pipeline de deploy...");
        System.out.println("Para começar o acompanhamento do processo de deploy, siga os passos abaixo:");
        System.out.println("Qual o id do pipeline que deseja acompanhar?");
        id = sc.nextLine();
        System.out.println("\nQual sistema estamos trabalhando nesse deploy?: ");
        sistema = sc.nextLine();
        System.out.println("\nQual o tempo em minutos, de execução do deploy?:");
        janelaTempo = sc.nextInt();
        sc.nextLine();
        pipelineDeploy = new PipelineDeploy(id, sistema, janelaTempo);
        System.out.println("\nPipeline criado com sucesso!");

        return pipelineDeploy;
    }

    public EtapaDeploy adicionarEtapa(PipelineDeploy pipelineDeploy, Scanner sc) {

        String descricao;
        int minutosEstimados = 0;
        int minutosExecutados = 0;
        int opcaoCriticidade;
        byte opcaoAdicionarEtapa;
        CriticidadeEtapa criticidadeEtapa;
        EtapaDeploy etapa;

        System.out.println("\n Agora iremos criar as etapas do deploy, para isso, siga os passos abaixo:");

        do {
            System.out.println("Qual a descrição da etapa?:");
            descricao = sc.nextLine();
            System.out.println("\nQual o tempo estimado para execução dessa etapa?:");
            minutosEstimados = sc.nextInt();
            sc.nextLine();
            System.out.println("\nQual o tempo executado para essa etapa?:");
            minutosExecutados = sc.nextInt();
            sc.nextLine();
            System.out.println("\nQual a criticidade dessa etapa?");
            System.out.println("1 - BAIXA");
            System.out.println("2 - MEDIA");
            System.out.println("3 - ALTA");

            opcaoCriticidade = sc.nextInt();
            sc.nextLine();

            switch (opcaoCriticidade) {
                case 1:
                    criticidadeEtapa = CriticidadeEtapa.BAIXA;
                    break;
                case 2:
                    criticidadeEtapa = CriticidadeEtapa.MEDIA;
                    break;
                case 3:
                    criticidadeEtapa = CriticidadeEtapa.ALTA;
                    break;
                default:
                    throw new IllegalArgumentException("Opção de criticidade inválida. Use 1, 2 ou 3.");
            }

            etapa = new EtapaDeploy(descricao, minutosEstimados, minutosExecutados, criticidadeEtapa);
            pipelineDeploy.adicionarEtapa(etapa);
            System.out.println("\nEtapa criada com sucesso!");

            System.out.println("\nDeseja adicionar mais uma etapa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcaoAdicionarEtapa = sc.nextByte();
            sc.nextLine();

        } while (opcaoAdicionarEtapa == 1);

        return etapa;
    }

    public void resumoFinal(PipelineDeploy pipelineDeploy) {

        System.out.println("\nResumo final do pipeline de deploy:");
        System.out.println("Pipeline: "+pipelineDeploy.getIdExecucao()+" | Sistema: "+pipelineDeploy.getSistema());
        System.out.println("Status: "+pipelineDeploy.getStatus().getDescricao());
        System.out.println("Progresso: "+pipelineDeploy.calcularProgressoDeploy()+"%");
        System.out.println("Previsão ponderada: "+pipelineDeploy.calcularPrevisaoTotalPonderada()+"min");
        System.out.println("Janela de mudança: "+pipelineDeploy.getJanelaMudancaMinutos()+"min");
        System.out.println("Situação da janela: "+pipelineDeploy.getSituacaoJanela().getDescricao());
        System.out.println("Risco operacional: "+pipelineDeploy.getRiscoOperacional().getDescricao());
        System.out.println("Índice de consumo da janela: "+pipelineDeploy.calcularIndiceConsumoJanela()+"%");
    }
}