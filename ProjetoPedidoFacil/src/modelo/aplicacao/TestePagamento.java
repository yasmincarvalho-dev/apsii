package aplicacao;

import modelo.CaixaCentral;
import pagamento.*;

public class TestePagamentos{

    public static void main(String[] args){
        CaixaCentral caixa = CaixaCentral.getInstancia();
        double saldoInicial = caixa.getSaldoTotal();

        conferir(caixa == CaixaCentral.getInstancia(), "Mesmo caixa");

        Pagamento pagamento = new PagamentoPix();
        conferir(pagamento.processarPagamento(20.00), "Pix aprovado");

        pagamento = new PagamentoCartao();
        conferir(pagamento.processarPagamento(35.00), "Cartao aprovado");

        pagamento = new PagamentoDinheiro(100.00);
        conferir(pagamento.processarPagamento(50.00), "Dinheiro aprovado");

        pagamento = new PagamentoDinheiro(5.00);
        conferir(!pagamento.processarPagamento(20.00), "Dinheiro insuficiente recusado");

        pagamento = new PagamentoPix();
        conferir(!pagamento.processarPagamento(-10.00), "Valor negativo recusado");

        double saldoEsperado = saldoInicial + 105.00;

        conferir(Math.abs(caixa.getSaldoTotal() - saldoEsperado) < 0.000001, "Saldo correto no caixa compartilhado");

        System.out.printf("Saldo final: R$ %.2f%n", caixa.getSaldoTotal());
        System.out.println("Todos os testes passaram.");
    }

    private static void conferir(boolean resultado, String descricao){
        if (!resultado){
            throw new IllegalStateException("Falhou: " + descricao);
        }
        System.out.println("OK - " + descricao);
    }
}