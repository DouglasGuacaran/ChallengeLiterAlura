package ChallengeLiterAlura.app.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) {
        System.out.println(">>> Iniciando solicitud a: " + url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.err.println(">>> IOException: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println(">>> InterruptedException: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String json = response.body();
        System.out.println(">>> Respuesta obtenida");
        return json;
    }
}

