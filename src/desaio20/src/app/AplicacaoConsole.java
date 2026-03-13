package desaio20.src.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import desaio20.src.domain.DadosAdicionais;
import desaio20.src.domain.DadosDigitais;
import desaio20.src.domain.DadosFisicos;
import desaio20.src.domain.DadosPerecivel;
import desaio20.src.domain.enums.CategoriaEstoque;
import desaio20.src.domain.enums.TipoMovimentacao;
import desaio20.src.dto.CadastrarProdutoRequest;
import desaio20.src.dto.MovimentacaoRequest;
import desaio20.src.dto.ProdutoResponse;
import desaio20.src.dto.RelatorioEstoqueResponse;
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
                dadosAdicionais);

        ProdutoResponse response = estoqueService.cadastrarProduto(request);

        System.out.println("Produto cadastrado: " + response.getNome());
    }

    private void movimentarEstoque() {

        System.out.println("=== PRODUTOS DISPONÍVEIS ===");
        List<ProdutoResponse> produtos = estoqueService.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (ProdutoResponse p : produtos) {
            System.out.println(
                    "ID: " + p.getId() + "| " + p.getNome() + " | Estoque: " + p.getQuantidadeEmEstoque() + "unidades");
        }

        TipoMovimentacao tipoMovimentacao = null;

        System.out.println("Qual tipo de movimentação você deseja fazer?:");
        System.out.println("1 - Adicionar quantidade");
        System.out.println("2 - Retirar quantidade");
        int opcaoMovimentacao = sc.nextInt();
        sc.nextLine();

        switch (opcaoMovimentacao) {

            case 1 -> tipoMovimentacao = TipoMovimentacao.ENTRADA;

            case 2 -> tipoMovimentacao = TipoMovimentacao.SAIDA;

            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        System.out.println("Informe o ID do produto:");
        String idProduto = sc.nextLine();

        System.out.println("Informe a quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        MovimentacaoRequest request = new MovimentacaoRequest(idProduto, quantidade, tipoMovimentacao);

        ProdutoResponse response = estoqueService.movimentarEstoque(request);

        System.out.println("Produto atualizado! Estoque atual: " + response.getQuantidadeEmEstoque());

    }

    private void consultarProduto() {

        System.out.println("=== PRODUTOS DISPONÍVEIS ===");
        List<ProdutoResponse> produtos = estoqueService.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (ProdutoResponse p : produtos) {
            System.out.println(
                    "ID: " + p.getId() + "| " + p.getNome() + " | Estoque: " + p.getQuantidadeEmEstoque() + "unidades");
        }

        System.out.println("Qual produto você deseja consultar?:");
        String consultarProduto = sc.nextLine();

        ProdutoResponse response = estoqueService.consultarProduto(consultarProduto);

        System.out.println("\n--- DETALHES DO PRODUTO ---");
        System.out.println("ID: " + response.getId());
        System.out.println("Produto: " + response.getNome());
        System.out.println("Preço base: " + response.getPrecoBase());
        System.out.println("Preço final: " + response.getPrecoFinal());
        System.out.println("Em estoque: " + response.getQuantidadeEmEstoque());
        System.out.println("Detalhes adicionais: " + response.getResumoDadosAdicionais());
        System.out.println("Abaixo do mínimo: " + (response.isAbaixoDoMinimo() ? "SIM" : "NÃO"));

    }

    private void gerarRelatorio() {
        RelatorioEstoqueResponse response = estoqueService.gerarRelatorio();

        System.out.println("=== Relatório Completo ====");
        System.out.println("Total de produtos: " + response.getTotalProdutos());
        System.out.println("Produtos abaixo da quantidade minima: " + response.getProdutosAbaixoDoMinimo());
        System.out.println("Produtos com estoque zerado: " + response.getProdutosSemEstoque());
        System.out.println("Valor total atual do estoque: R$ %.2f%n" + response.getValorTotalEmEstoque());

        System.out.println("\n--- Quantidade por Categoria ---");
        response.getQuantidadePorCategoria().forEach((categoria, quantidade) -> 
            System.out.println(" " + categoria + ": " + quantidade + "unidades")
        );
        System.out.println("\n--- Alertas de Validade ---");
        if (response.getAlertasValidade().isEmpty()) {
            System.out.println("Nenhum produto com validade próxima.");
        } else {
            response.getAlertasValidade().forEach(alerta ->
                System.out.println(" ⚠" + alerta)
            );
        }
    }
}