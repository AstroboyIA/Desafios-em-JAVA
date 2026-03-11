package desaio20.src.app;

import java.util.List;
import java.util.Scanner;

import desaio20.src.dto.MovimentacaoRequest;
import desaio20.src.listener.AlertaReposicaoListener;
import desaio20.src.listener.LogListener;
import desaio20.src.listener.RelatorioEventosListener;
import desaio20.src.pricing.Precificavel;
import desaio20.src.pricing.PrecoNormal;
import desaio20.src.repository.ProdutoRepository;
import desaio20.src.repository.ProdutoRepositoryEmMemoria;
import desaio20.src.service.EstoqueService;
import desaio20.src.service.PrecificacaoService;

public class AplicacaoConsole {

    private final EstoqueService estoqueService;
    private final Scanner sc;

    public AplicacaoConsole() {

        ProdutoRepository repository = new ProdutoRepositoryEmMemoria();

        LogListener log = new LogListener();
        AlertaReposicaoListener alerta = new AlertaReposicaoListener();
        RelatorioEventosListener relatorioEventos = new RelatorioEventosListener();

        PrecificacaoService precificacao = new PrecificacaoService(new PrecoNormal());

        this.estoqueService = new EstoqueService(
                repository,
                precificacao,
                List.of(log, alerta, relatorioEventos));

        this.sc = new Scanner(System.in);
    }

    public void iniciar() {

        boasVindas();
        int opcao;

        do{
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            processarOpcao(opcao);
        } while (opcao != 0);

    }

    private void boasVindas() {

        System.out.println("Boas vindas ao Sistema de Gerenciamento de Estoque");

    }

    private void exibirMenu() {
        System.out.println("/n===Menu===");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Movimentar Estoque");
        System.out.println("3 - Consultar Produto");
        System.out.println("4 - Gerar Relatório");
        System.out.println("0 - Sair");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarProduto();
                break;

            case 2:
                movimentarEstoque();
                break;

            case 3:
                consultarProduto();
                break;

            case 4:
                gerarRelatorio();
                break;

            case 0:
                System.out.println("Encerrando...");
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void cadastrarProduto() {
    }

    private void movimentarEstoque() {

    }

    private void consultarProduto() {

    }

    private void gerarRelatorio() {

    }
}