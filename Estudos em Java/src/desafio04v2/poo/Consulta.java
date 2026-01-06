package desafio04v2.poo;

import desafio04v2.poo.interfaces.Atendimento;

public class Consulta  implements Atendimento {
    @Override
    public void realizarProcedimento() {
        System.out.println("Realizando consulta de rotina. Verificando sinais vitais.");
    }
}