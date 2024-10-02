import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class requestAPIMoeda {

    public conversaoResult valorMoeda(String base_code, String target_code, double amount) {
        URI moeda = URI.create("https://v6.exchangerate-api.com/v6/be25b190c54d8212655eb1a6/pair/" + base_code + "/" + target_code + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(moeda)
                .build();

        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possivel obter o valor da moeda");
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na resposta da API: " + response.statusCode());
        }

        try {
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            double conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
            double convertedAmount = amount * conversionRate;
            return new conversaoResult(conversionRate, convertedAmount);
        } catch (JsonParseException | NullPointerException e) {
            throw new RuntimeException("Erro ao processar a resposta da API", e);
        }
    }

}
