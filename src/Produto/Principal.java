package Produto;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Livro livro = new Livro();
        CD cd = new CD();

        System.out.println("=== CADASTRO DE LIVRO ===");
        System.out.print("Digite o nome do livro: ");
        livro.setNome(scanner.nextLine());

        System.out.print("Digite o preço do livro: ");
        livro.setPreco(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        livro.setAutor(scanner.nextLine());

        System.out.println("\n=== CADASTRO DE CD ===");
        System.out.print("Digite o nome do CD: ");
        cd.setNome(scanner.nextLine());

        System.out.print("Digite o preço do CD: ");
        cd.setPreco(scanner.nextDouble());

        System.out.print("Digite o número de faixas do CD: ");
        cd.setNumFaixas(scanner.nextInt());

        cd.exibeInformacoes();

        scanner.close();
    }
}