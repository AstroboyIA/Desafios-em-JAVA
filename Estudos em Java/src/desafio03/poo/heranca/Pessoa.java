package desafio03.poo.heranca;

public class Pessoa {
    private String nome;
    private int id;

    public Pessoa(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public void exibirInfo(){
        System.out.println("Nome: " + getNome());
        System.out.println("Id: " + getId());
    }

    public void setNota(double nota) {
    }

    public void setDisciplina(String disciplina) {
    }
}

