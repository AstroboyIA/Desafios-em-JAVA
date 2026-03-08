package desaio20.src.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import desaio20.src.domain.EventoEstoque;
import desaio20.src.domain.Produto;
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

    public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request, ProdutoRepository repository) {

        String idGerado = UUID.randomUUID().toString();

        Produto<?> produto = new Produto<>(
                idGerado,
                request.getNome(),
                request.getCategoria(),
                request.getPrecoBase(),
                request.getQuantidadeInicial(),
                request.getDadosAdicionais());

        repository.salvar(produto);

        return null;

    }

    public ProdutoResponse movimentarEstoque(MovimentacaoRequest request) {

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
            } else if (produto.getQuantidadeEmEstoque() < produto.getCategoria().getQuantidadeMinimaRecomendada()) {
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

        return null;
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
            produto.getQuantidadeEmEstoque() < produto.getCategoria().getQuantidadeMinimaRecomendada()
        );
    }

    public RelatorioEstoqueResponse gerarRelatorio() {
        return null;
    }
}
