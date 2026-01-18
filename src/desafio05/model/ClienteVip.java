package desafio05.model;

// precisa receber valor final e aplicar 10% de desconto
public class ClienteVip extends Cliente {
    final Double percentualDesconto = 10.0;

    public ClienteVip(String nomeCliente) {
        super(nomeCliente);
    }

    @Override
    public Double calcularValorFinal(Pedido pedido) {
        Double total = pedido.contaValorTotal();
        Double desconto = total * (percentualDesconto / 100);
        return total - desconto;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }
}