package desafio19.service;

import desafio19.dto.ResultadoAutorizacaoDTO;
import desafio19.dto.TransacaoDTO;
import desafio19.model.ContaCartao;
import desafio19.model.TransacaoFinanceira;
import desafio19.model.enums.NivelFraude;
import desafio19.model.enums.StatusAutorizacao;

public class AutorizacaoService {

    public ResultadoAutorizacaoDTO processarTransacao(
            TransacaoDTO dto,
            ContaCartao conta
        ) {

            TransacaoFinanceira transacao = new TransacaoFinanceira(

                dto.id(),
                dto.valor(),
                dto.categoria(),
                dto.canal(),
                dto.minutosDoDia(),
                conta
            
            );

            int score = transacao.calcularScoreAntifraude();
            NivelFraude nivel = transacao.classificarFraude();
            StatusAutorizacao status = transacao.autorizar()

        return new ResultadoAutorizacaoDTO(
            status,
            nivel,
            score,
            conta.obterCriticidade(),
            conta.calcularIndiceExposicao()
        );
    }
}