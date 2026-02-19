package desafio16.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import main.desafio16.model.AtividadeAtendimento;
import main.desafio16.model.ChamadoSuporte;
import main.desafio16.model.StatusChamado;

public class ChamadoSuporteTest {

    @Test
    void chamadoSemAtividadesDeveEstarAberto() {
        ChamadoSuporte chamado = new ChamadoSuporte("CH-01", "Cliente X", 100);

        assertEquals(StatusChamado.ABERTO, chamado.getStatus());
    }

    @Test
    void chamadoComAtividadesConcluidasDeveSerResolvido() {
        ChamadoSuporte chamado = new ChamadoSuporte("CH-01", "Cliente X", 200);

        AtividadeAtendimento atividade = new AtividadeAtendimento("Configuração", 50, 50);

        chamado.adicionarAtividade(atividade);

        assertEquals(StatusChamado.RESOLVIDO, chamado.getStatus());
    }

    @Test
    void deveCalcularProgressoCorretamente() {
        ChamadoSuporte chamado = new ChamadoSuporte("CH-01", "Cliente X", 300);

        chamado.adicionarAtividade(new AtividadeAtendimento("Parte 1", 100, 50));

        chamado.adicionarAtividade(new AtividadeAtendimento("Parte 2", 100, 50));

        assertEquals(50, chamado.calcularProgressoAtendimento());
    }

    @Test
    void indiceSlaNaoDeveUltrapassar100() {
        ChamadoSuporte chamado = new ChamadoSuporte("CH-01", "Cliente X", 100);

        chamado.adicionarAtividade( new AtividadeAtendimento("Teste", 200, 150));

        assertEquals(100, chamado.calcularIndiceConsumoSLA());
    }
}
