package desaio20.src.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import desaio20.src.domain.DadosAdicionais;
import desaio20.src.domain.DadosDigitais;
import desaio20.src.domain.DadosFisicos;
import desaio20.src.domain.DadosPerecivel;
import desaio20.src.domain.enums.CategoriaEstoque;
import desaio20.src.dto.CadastrarProdutoRequest;
import desaio20.src.dto.MovimentacaoRequest;
import desaio20.src.dto.ProdutoResponse;
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

        do {
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
        System.out.println("\n===Menu===");
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

        CategoriaEstoque categoria = null;

        System.out.println("\nPara cadastrar o produto, informe os dados abaixo.");

        System.out.println("\nQual produto será adicionado ao estoque?");
        String nomeProduto = sc.nextLine();
        System.out.println("/nQual categoria pertenco o produto?:");
        System.out.println("1 - Eletrônicos");
        System.out.println("2 - Alimentício");
        System.out.println("3 - Vestuário");
        System.out.println("4 - Digital");
        System.out.println("5 - Perecível");
        int opcaoCategoria = sc.nextInt();
        sc.nextLine();

        switch (opcaoCategoria) {
            case 1:
                categoria = CategoriaEstoque.ELETRONICO;
                break;
            case 2:
                categoria = CategoriaEstoque.ALIMENTICIO;
                break;
            case 3:
                categoria = CategoriaEstoque.VESTUARIO;
                break;
            case 4:
                categoria = CategoriaEstoque.DIGITAL;
                break;
            case 5:
                categoria = CategoriaEstoque.PERECIVEL;

            default:
                System.out.println("Opção inválida!");
                break;
        }

        if (categoria == null) {
            throw new IllegalArgumentException("É necessario informa uma categoria válida!");
        }

        System.out.println("\nQual o preço do produto?:");
        double precoProduto = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nQual a quantidade de produtos que será adicionada ao estoque?:");
        int quantidadeProdutos = sc.nextInt();
        sc.nextLine();

        DadosAdicionais dadosAdicionais;

        switch (opcaoCategoria) {
            case 1, 3 -> {
                System.out.println("Peso (kg): ");
                double peso = sc.nextDouble();
                sc.nextLine();
                System.out.println("Dimensões ( ex: 30x20x10 ): ");
                String dimensoes = sc.nextLine();
                System.out.println("Precisa de refrigeração? ( SIM / NÃO )");
                boolean refrigeracao = sc.nextLine().equalsIgnoreCase("SIM");
                dadosAdicionais = new DadosFisicos(peso, dimensoes, refrigeracao);
            }
            case 2, 5 -> {
                System.out.println("Data de válidade (AAAA-MM-DD): ");
                LocalDate validade = LocalDate.parse(sc.nextLine());
                System.out.println("Lote de rastreamento: ");
                String rastreamento = sc.nextLine();
                dadosAdicionais = new DadosPerecivel(validade, rastreamento);
            }
            case 4 -> {
                System.out.println("URL de download: ");
                String url = sc.nextLine();
                System.out.println("Tamanho (MB): ");
                double tamanho = sc.nextDouble();
                sc.nextLine();
                System.out.println("Plataforma: ");
                String plataforma = sc.nextLine();
                dadosAdicionais = new DadosDigitais(url, tamanho, plataforma);
            }
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        CadastrarProdutoRequest request = new CadastrarProdutoRequest(
            nomeProduto,
            categoria,
            precoProduto,
            quantidadeProdutos,
            dadosAdicionais
        );

        ProdutoResponse response = estoqueService.cadastrarProduto(request);

        System.out.println("Produto cadastrado: " + response.getNome());
    }

    private void movimentarEstoque() {

    }

    private void consultarProduto() {

    }

    private void gerarRelatorio() {

    }
}