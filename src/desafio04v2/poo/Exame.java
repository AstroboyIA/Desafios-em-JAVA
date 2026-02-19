package main.desafio04v2.poo;

import main.desafio04v2.poo.interfaces.Atendimento;

public class Exame implements Atendimento {
    @Override
    public void realizarProcedimento() {
        System.out.println("Realizando exame laboratorial. Coletando amostras.");
    }
}