package desafio21.src.repository;

import java.util.List;
import java.util.Optional;

import desafio21.src.domain.Quarto;

public interface QuartoRepository {
    
    void salvar(Quarto quarto);
    Optional<Quarto> buscarPorNumero(String nuemro);
    List<Quarto> buscarTodos();
    List<Quarto> buscarDisponiveis();
    void atualizar(Quarto quarto);
    
}
