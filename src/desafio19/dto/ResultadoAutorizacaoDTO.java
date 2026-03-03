package desafio19.dto;

import desafio19.model.enums.CriticidadeConta;
import desafio19.model.enums.NivelFraude;
import desafio19.model.enums.StatusAutorizacao;

public record ResultadoAutorizacaoDTO(
    StatusAutorizacao status,
    NivelFraude nivelFraude,
    int score,
    CriticidadeConta criticidade,
    double indiceExposicao
) {}