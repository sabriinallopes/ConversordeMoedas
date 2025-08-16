import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Conversor de Moedas ===");
        System.out.println("1 - Dólar para Real");
        System.out.println("2 - Real para Dólar");
        System.out.println("3 - Euro para Real");
        System.out.println("4 - Real para Euro");
        System.out.println("5 - Libra para Real");
        System.out.println("6 - Real para Libra");
        System.out.print("Escolha uma opção (1-6): ");
        int opcao = scanner.nextInt();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        String origem = "", destino = "";

        switch (opcao) {
            case 1 -> { origem = "USD"; destino = "BRL"; }
            case 2 -> { origem = "BRL"; destino = "USD"; }
            case 3 -> { origem = "EUR"; destino = "BRL"; }
            case 4 -> { origem = "BRL"; destino = "EUR"; }
            case 5 -> { origem = "GBP"; destino = "BRL"; }
            case 6 -> { origem = "BRL"; destino = "GBP"; }
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        double taxa = ConversorAPI.buscarTaxa(origem, destino);
        if (taxa == -1) {
            System.out.println("Erro ao buscar taxa de conversão.");
            return;
        }

        double convertido = valor * taxa;
        System.out.printf("💱 %.2f %s equivalem a %.2f %s%n", valor, origem, convertido, destino);
    }
}
