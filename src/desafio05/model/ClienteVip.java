package desafio05.model;


// precisa receber valor final e aplicar 10% de desconto
public class ClienteVip extends Cliente{
    final Double percentualDesconto = 10.0;

    public ClienteVip (String nomeCliente){
        super(nomeCliente);
    }
    public Double aplicarDesconto(Double valorDesconto){
        Double desconto = valorDesconto * (percentualDesconto / 100);
        return valorDesconto - desconto;
    }
}
