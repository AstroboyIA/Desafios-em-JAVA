package desafio16.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import desafio16.model.AtividadeAtendimento;

public class AtividadeAtendimentoTest {

    @Test
    void deveCriarAtividadeValida() {
        AtividadeAtendimento atividade = new AtividadeAtendimento("Instalar software", 60, 30);

        assertEquals(60, atividade.getMinutosEstimados());
    }

    @Test
    void deveLancarExcecaoParaEstimadoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new AtividadeAtendimento("Teste", 0, 0));
    }

    @Test
    void deveLancarExcecaoParaExecutadoMaiorQueEstimado() {
        assertThrows(IllegalArgumentException.class, () -> new AtividadeAtendimento("Teste", 10, 20));
    }
}
