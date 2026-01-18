package desafio05.model;

public class Cliente {
    private String nomeCliente;

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double calcularValorFinal(Pedido pedido) {
        return pedido.contaValorTotal();
    }
}