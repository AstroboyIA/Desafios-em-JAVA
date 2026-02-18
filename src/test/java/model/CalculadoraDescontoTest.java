package test.java.model;

import org.junit.jupiter.api.Test;

import main.aprendendoTestesAutomatizados.model.calculadoraDesconto;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraDescontoTest {

    @Test
    void deveCalcularDescontoCorretamente() {
        CalculadoraDesconto calculadora = new calculadoraDesconto();

        double resultado = calculadora.calcularDesconto(100, 10);

        assertEquals(90.0, resultado);
    }

    @Test
    void deveLancarExecaoQuandoPercentualMaiorQue100() {
        CalculadoraDesconto calculadora = new calculadoraDesconto();

        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularDesconto(100, 150);
        });
    }

    @Test
    void deveLancarExcecaoQuandoValorNegativo() {
        CalculadoraDesconto calculadora = new calculadoraDesconto();

        assertThrowa(IllegalArgumentException.class, () -> {
            calculadora.calcularDesconto(-50, 10);
        });
    }
}