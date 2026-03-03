package desafio19.dto;

import desafio19.model.enums.CanalTransacao;
import desafio19.model.enums.CategoriaTransacao;

public record TransacaoDTO(
    
    String id,
    double valor,
    CategoriaTransacao categoria,
    CanalTransacao canal,
    int minutosDoDia

) {}
