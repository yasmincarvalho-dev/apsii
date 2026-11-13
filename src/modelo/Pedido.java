package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<String> itens;
    private double valorTotal;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(String nomeItem, double preco) {
        itens.add(nomeItem + " - R$ " + preco);
        valorTotal += preco;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void exibirResumo() {
        System.out.println("--- Pedido #" + numeroPedido + " ---");
        for (String item : itens) {
            System.out.println(" * " + item);
        }
        System.out.println("Total a pagar: R$ " + valorTotal);
    }
}