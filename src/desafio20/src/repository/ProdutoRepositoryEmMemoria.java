package desafio20.src.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import desafio20.src.domain.Produto;

public class ProdutoRepositoryEmMemoria implements ProdutoRepository {
    
    private final Map<String, Produto<?>> produtos = new HashMap<>();

    @Override
    public void salvar(Produto<?> produto) {
        produtos.put(produto.getId(), produto);
    }

    @Override
    public Optional<Produto<?>> buscarPorId(String id) {
        return Optional.ofNullable(produtos.get(id));
    }

    @Override
    public List<Produto<?>> buscarTodos() {
        return List.copyOf(produtos.values());
    }

    @Override
    public boolean existePorId(String id) {
        return produtos.containsKey(id);
    }

    @Override
    public void atualizar(Produto<?> produto) {
        String id = produto.getId();

        if (produtos.containsKey(id)) {
            produtos.put(id, produto);
            System.out.println("Produto " + id + " atualizado com sucesso!");
        } else {
            System.out.println("Produto " + id + " não encontrado!");
        }
    }
}
