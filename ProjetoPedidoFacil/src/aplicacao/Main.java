package aplicacao;
import java.util.Scanner;
import modelo.CaixaCentral;
import modelo.Pedido;
import pagamento.Pagamento;
import pagamento.PagamentoCartao;
import pagamento.PagamentoDinheiro;
import pagamento.PagamentoPix;

public class Main{

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        CaixaCentral caixa = CaixaCentral.getInstancia();
        int proximoNumeroPedido = 1;
        int opcao;

        System.out.println("====================================");
        System.out.println("      BEM-VINDO AO PEDIDO FACIL");
        System.out.println("====================================");

        do{
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao){
                case 1:
                    Pedido pedido = new Pedido(proximoNumeroPedido);
                    boolean temItens = montarPedido(pedido);

                    if (temItens){
                        pedido.exibirResumo();
                        boolean pagamentoAprovado = realizarPagamento(pedido.getValorTotal());

                        if (pagamentoAprovado){
                            System.out.println("Pedido finalizado com sucesso!");
                            proximoNumeroPedido++;
                        } else{
                            System.out.println("Pedido não foi finalizado.");
                        }
                    } else{
                        System.out.println("Pedido vazio. Nenhum pagamento foi realizado.");
                    }
                    break;

                case 2:
                    System.out.printf("Saldo atual do caixa: R$ %.2f%n", caixa.getSaldoTotal());
                    break;

                case 0:
                    System.out.println("Encerrando o PedidoFacil. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal(){
        System.out.println("\n----------- MENU PRINCIPAL -----------");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Consultar saldo do caixa");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------------");
    }

    private static boolean montarPedido(Pedido pedido){
        int opcao;

        do{
            System.out.println("\n--------------- CARDÁPIO ---------------");
            System.out.println("1 - Hambúrguer       R$ 15,00");
            System.out.println("2 - X-Salada         R$ 18,00");
            System.out.println("3 - Batata frita     R$ 10,00");
            System.out.println("4 - Refrigerante     R$ 6,00");
            System.out.println("5 - Suco             R$ 7,50");
            System.out.println("0 - Finalizar escolha dos itens");
            System.out.println("----------------------------------------");
            opcao = lerInteiro("Escolha um item: ");

            switch (opcao){
                case 1:
                    pedido.adicionarItem("Hambúrguer", 15.00);
                    System.out.println("Hambúrguer adicionado ao pedido.");
                    break;
                case 2:
                    pedido.adicionarItem("X-Salada", 18.00);
                    System.out.println("X-Salada adicionado ao pedido.");
                    break;
                case 3:
                    pedido.adicionarItem("Batata frita", 10.00);
                    System.out.println("Batata frita adicionada ao pedido.");
                    break;
                case 4:
                    pedido.adicionarItem("Refrigerante", 6.00);
                    System.out.println("Refrigerante adicionado ao pedido.");
                    break;
                case 5:
                    pedido.adicionarItem("Suco", 7.50);
                    System.out.println("Suco adicionado ao pedido.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Item inválido. Escolha uma opção do cardápio.");
            }
        } while (opcao != 0);

        return pedido.getValorTotal() > 0;
    }

    private static boolean realizarPagamento(double valorTotal){
        int opcaoPagamento;

        do{
            System.out.println("\n----------- FORMAS DE PAGAMENTO -----------");
            System.out.printf("Total do pedido: R$ %.2f%n", valorTotal);
            System.out.println("1 - Pix");
            System.out.println("2 - Cartão");
            System.out.println("3 - Dinheiro");
            System.out.println("0 - Cancelar pagamento");
            System.out.println("-------------------------------------------");
            opcaoPagamento = lerInteiro("Escolha a forma de pagamento: ");

            Pagamento pagamento;

            switch (opcaoPagamento){
                case 1:
                    pagamento = new PagamentoPix();
                    if (pagamento.processarPagamento(valorTotal)){
                        return true;
                    }
                    System.out.println("Tente novamente ou escolha outra forma de pagamento.");
                    break;

                case 2:
                    pagamento = new PagamentoCartao();
                    if (pagamento.processarPagamento(valorTotal)){
                        return true;
                    }
                    System.out.println("Tente novamente ou escolha outra forma de pagamento.");
                    break;

                case 3:
                    double valorRecebido = lerValor("Digite o valor recebido: R$ ");
                    pagamento = new PagamentoDinheiro(valorRecebido);
                    if (pagamento.processarPagamento(valorTotal)){
                        return true;
                    }
                    System.out.println("Tente novamente ou escolha outra forma de pagamento.");
                    break;

                case 0:
                    System.out.println("Pagamento cancelado.");
                    return false;

                default:
                    System.out.println("Opção inválida. Escolha uma forma de pagamento.");
            }
        } while (true);
    }

    private static int lerInteiro(String mensagem){
        while (true){
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try{
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerValor(String mensagem){
        while (true){
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(',', '.');

            try{
                double valor = Double.parseDouble(entrada);
                if (Double.isFinite(valor) && valor >= 0){
                    return valor;
                }
                System.out.println("Digite um valor válido e não negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Exemplo: 20,00");
            }
        }
    }
}