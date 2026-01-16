package desafio05.model;


// precisa receber valor final e aplicar 10% de desconto
public class ClienteVip extends Cliente{
    protected Integer desconto;

    public ClienteVip (String nomeCliente, Integer desconto){
        super(nomeCliente);
        this.desconto = desconto;
    }
}
