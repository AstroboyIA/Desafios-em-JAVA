package desaio20.src.repository;

import java.util.List;
import java.util.Optional;

import desaio20.src.domain.Produto;

public interface ProdutoRepository {
    
    void salvar(Produto<?> produto);
    Optional<Produto<?>> buscarPorId(String id);
    List<Produto<?>> buscarTodos();
    boolean existePorId(String id);
    void atualizar(Produto<?> produto);
    
}