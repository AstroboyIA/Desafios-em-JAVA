package desaio20.src.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import desaio20.src.domain.DadosAdicionais;
import desaio20.src.domain.EventoEstoque;
import desaio20.src.domain.Produto;
import desaio20.src.domain.enums.CategoriaEstoque;
import desaio20.src.domain.enums.TipoEvento;
import desaio20.src.domain.enums.TipoMovimentacao;
import desaio20.src.dto.CadastrarProdutoRequest;
import desaio20.src.dto.MovimentacaoRequest;
import desaio20.src.dto.ProdutoResponse;
import desaio20.src.dto.RelatorioEstoqueResponse;
import desaio20.src.listener.EventoListener;
import desaio20.src.repository.ProdutoRepository;

public class EstoqueService {

    private ProdutoRepository repository;
    private PrecificacaoService precificacaoService;
    private List<EventoListener> listeners;

    public EstoqueService(ProdutoRepository repository, PrecificacaoService precificacaoService,
            List<EventoListener> listeners) {
        this.repository = repository;
        this.precificacaoService = precificacaoService;
        this.listeners = listeners;
    }

    private void publicarEvento(EventoEstoque evento) {
        for (EventoListener listener : listeners) {
            listener.aoReceberEvento(evento);
        }
    }

    public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Por favor, preencha os dados antes de finalizar.");
        }

        String idGerado = UUID.randomUUID().toString();

        Produto<?> produto = new Produto<>(
                idGerado,
                request.getNome(),
                request.getCategoria(),
                request.getPrecoBase(),
                request.getQuantidadeInicial(),
                request.getDadosAdicionais());

        repository.salvar(produto);

        publicarEvento(
                new EventoEstoque(
                        TipoEvento.PRODUTO_CADASTRADO,
                        produto.getId(),
                        produto.getNome(),
                        0,
                        produto.getQuantidadeEmEstoque(),
                        LocalDateTime.now()));

        double precoFinal = precificacaoService.calcularPreco(produto.getPrecoBase());

        return new ProdutoResponse(
                idGerado,
                produto.getNome(),
                produto.getCategoria(),
                produto.getPrecoBase(),
                precoFinal,
                produto.getQuantidadeEmEstoque(),
                produto.getDadosAdicionais().resumo(),
                produto.isAbaixoDoMinimo());
    }

    public ProdutoResponse movimentarEstoque(MovimentacaoRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Por favor, preencha os dados antes de finalizar.");
        }

        Produto<?> produto = repository.buscarPorId(request.getIdProduto())
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado!"));

        int quantidadeAnterior = produto.getQuantidadeEmEstoque();

        if (request.getTipo() == TipoMovimentacao.ENTRADA) {
            produto.adicionarQuantidadeAoEstoque(request.getQuantidade());
        } else {
            produto.removerQuantidadeDoEstoque(request.getQuantidade());
        }

        repository.atualizar(produto);

        TipoEvento tipoEvento = request.getTipo() == TipoMovimentacao.ENTRADA
                ? TipoEvento.ENTRADA_ESTOQUE
                : TipoEvento.SAIDA_ESTOQUE;

        EventoEstoque evento = new EventoEstoque(
                tipoEvento,
                produto.getId(),
                produto.getNome(),
                quantidadeAnterior,
                produto.getQuantidadeEmEstoque(),
                LocalDateTime.now());

        publicarEvento(evento);

        if (request.getTipo() == TipoMovimentacao.SAIDA) {

            if (produto.getQuantidadeEmEstoque() == 0) {
                publicarEvento(
                        new EventoEstoque(
                                TipoEvento.ESTOQUE_ZERADO,
                                produto.getId(),
                                produto.getNome(),
                                quantidadeAnterior,
                                0,
                                LocalDateTime.now()));
            } else if (produto.isAbaixoDoMinimo()) {
                publicarEvento(
                        new EventoEstoque(
                                TipoEvento.ESTOQUE_MINIMO_ATINGIDO,
                                produto.getId(),
                                produto.getNome(),
                                quantidadeAnterior,
                                produto.getQuantidadeEmEstoque(),
                                LocalDateTime.now()));
            }
        }

        double precoFinal = precificacaoService.calcularPreco(produto.getPrecoBase());

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPrecoBase(),
                precoFinal,
                produto.getQuantidadeEmEstoque(),
                produto.getDadosAdicionais().resumo(),
                produto.isAbaixoDoMinimo()
            );
    }

    public ProdutoResponse consultarProduto(String idProduto) {

        Produto<?> produto = repository.buscarPorId(idProduto)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado!"));

        double precoFinal = precificacaoService.calcularPreco(produto.getPrecoBase());

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPrecoBase(),
                precoFinal,
                produto.getQuantidadeEmEstoque(),
                produto.getDadosAdicionais().resumo(),
                produto.isAbaixoDoMinimo());
    }

    public RelatorioEstoqueResponse gerarRelatorio() {

        List<Produto<?>> produtos = repository.buscarTodos();
        List<String> alertasValidade = new ArrayList<>();
        int totalEmEstoque = 0;
        int produtosSemEstoque = 0;
        int produtosAbaixoDoMinimo = 0;
        double valorTotalEmEstoque = 0.0;

        Map<CategoriaEstoque, Integer> quantidadePorCategoria = new HashMap<>();

        for (Produto<?> produto : produtos) {
            produto.getDadosAdicionais().verificarAlertas(produto, alertasValidade, this::publicarEvento);

            totalEmEstoque += produto.getQuantidadeEmEstoque();
            valorTotalEmEstoque += produto.getPrecoBase() * produto.getQuantidadeEmEstoque();

            quantidadePorCategoria.merge(
                    produto.getCategoria(),
                    produto.getQuantidadeEmEstoque(),
                    Integer::sum);

            if (produto.getQuantidadeEmEstoque() == 0) {
                produtosSemEstoque++;
            } else if (produto.isAbaixoDoMinimo()) {
                produtosAbaixoDoMinimo++;
            }
        }

        return new RelatorioEstoqueResponse.Builder()
                .totalProdutos(produtos.size())
                .produtosAbaixoDoMinimo(produtosAbaixoDoMinimo)
                .produtosSemEstoque(produtosSemEstoque)
                .valorTotalEmEstoque(valorTotalEmEstoque)
                .quantidadePorCategoria(quantidadePorCategoria)
                .alertasValidade(alertasValidade)
                .build();
    }
}
