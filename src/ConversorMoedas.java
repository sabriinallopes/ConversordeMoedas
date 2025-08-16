import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorMoedas {

    private static final String API_KEY = "d9fc75be6ad323c28f1bde3a"; // Substitua pela sua chave
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/d9fc75be6ad323c28f1bde3a/latest/USD";

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

        String moedaOrigem = "", moedaDestino = "";

        switch (opcao) {
            case 1 -> { moedaOrigem = "USD"; moedaDestino = "BRL"; }
            case 2 -> { moedaOrigem = "BRL"; moedaDestino = "USD"; }
            case 3 -> { moedaOrigem = "EUR"; moedaDestino = "BRL"; }
            case 4 -> { moedaOrigem = "BRL"; moedaDestino = "EUR"; }
            case 5 -> { moedaOrigem = "GBP"; moedaDestino = "BRL"; }
            case 6 -> { moedaOrigem = "BRL"; moedaDestino = "GBP"; }
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        double taxaConversao = buscarTaxaConversao(moedaOrigem, moedaDestino);
        if (taxaConversao == -1) {
            System.out.println("Erro ao buscar taxa de conversão.");
            return;
        }

        double valorConvertido = valor * taxaConversao;
        System.out.printf("💱 %.2f %s equivalem a %.2f %s%n", valor, moedaOrigem, valorConvertido, moedaDestino);
    }

    public static double buscarTaxaConversao(String origem, String destino) {
        try {
            URL url = new URL(BASE_URL + origem);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(conexao.getInputStream());
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            JsonObject rates = json.getAsJsonObject("rates");
            return rates.get(destino).getAsDouble();

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
