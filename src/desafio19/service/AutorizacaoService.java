package desafio19.service;

import java.util.Scanner;

import desafio19.dto.TransacaoDTO;
import desafio19.model.ContaCartao;
import desafio19.model.TransacaoFinanceira;
import desafio19.model.enums.CanalTransacao;
import desafio19.model.enums.CategoriaTransacao;
import desafio19.model.enums.PerfilRiscoCliente;
import desafio19.model.enums.StatusAutorizacao;

public class AutorizacaoService {

    public StatusAutorizacao processarTransacao(
            TransacaoDTO dto,
            ContaCartao conta
        ) {

            TransacaoFinanceira transacao = new TransacaoFinanceira(

                dto.id(),
                dto.valor(),
                dto.categoria(),
                dto.canal(),
                dto.minutosDoDia()
                conta
            
            );

        return transacao.autorizar();
    }
}