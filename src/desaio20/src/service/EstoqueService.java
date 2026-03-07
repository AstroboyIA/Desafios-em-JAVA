package desaio20.src.service;

import java.util.List;

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

    public EstoqueService(ProdutoRepository repository, PrecificacaoService precificacaoService, List<EventoListener> listeners) {
        this.repository = repository;
        this.precificacaoService = precificacaoService;
        this.listeners = listeners;
    }

    public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request);

    public ProdutoResponse movimentarEstoque(MovimentacaoRequest request);

    public ProdutoResponse consultarProduto(String idProduto);

    public RelatorioEstoqueResponse gerarRelatorio();
}
