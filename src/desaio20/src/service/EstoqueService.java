package desaio20.src.service;

import java.util.List;

import desaio20.src.dto.CadastrarProdutoRequest;
import desaio20.src.dto.MovimentacaoRequest;
import desaio20.src.dto.ProdutoResponse;
import desaio20.src.dto.RelatorioEstoqueResponse;
import desaio20.src.repository.ProdutoRepository;

public class EstoqueService {
    
    public EstoqueService(ProdutoRepository repository, PrecificacaoService precificacaoService, List<EventoListener> listeners) {}

    public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request);

    public ProdutoResponse movimentarEstoque(MovimentacaoRequest request);

    public ProdutoResponse consultarProduto(String idProduto);

    public RelatorioEstoqueResponse gerarRelatorio();
}
