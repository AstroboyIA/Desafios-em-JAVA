package desafio03.poo;

import desafio03.poo.heranca.Pessoa;

public class Professor extends Pessoa {
    private String disciplina;

    public Professor(String nome, int id, String disciplina) {
        super(nome, id);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void exibirInfo(){
        System.out.println("Nome: " + getNome());
        System.out.println("Id: " + getId());
        System.out.println("Disciplina: " + getDisciplina());
    }
}