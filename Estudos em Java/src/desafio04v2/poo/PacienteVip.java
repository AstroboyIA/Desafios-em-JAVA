package desafio04v2.poo;

import desafio04v2.poo.heranca.Paciente;

public class PacienteVip extends Paciente {
    protected String beneficioExtra;

    public PacienteVip(String nome, Byte idade, String beneficioExtra) {
        super(nome, idade);
        this.beneficioExtra = beneficioExtra;
    }

    public void exibirDados(){
        super.exibirDados();
        System.out.println("Benefício Extra: " + beneficioExtra);
    }
}