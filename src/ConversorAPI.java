import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorAPI {

    public static double buscarTaxa(String origem, String destino) {
        try {
            String urlStr = "https://open.er-api.com/v6/latest/" + origem;
            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            JsonObject json = JsonParser.parseString(resposta.toString()).getAsJsonObject();
            JsonObject rates = json.getAsJsonObject("rates");
            JsonElement taxa = rates.get(destino);

            return taxa.getAsDouble();

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
