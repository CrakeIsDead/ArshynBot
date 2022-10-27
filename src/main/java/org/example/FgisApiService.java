package org.example;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.example.resources.Constants.*;

public class FgisApiService {

    @SneakyThrows
    public Transcript vriGetRequest (String mi_number) {

        HttpRequest getRequest = HttpRequest.newBuilder ()
                .uri (new URI (FGIS_API_URL + SEARCH_PREFIX + URLEncoder.encode (mi_number, StandardCharsets.UTF_8)))
                .header (USER_PARAMS_NAME, USER_PARAMS_VALUE)
                .build ();
        System.out.println(getRequest.uri().toString());
        HttpClient httpClient = HttpClient.newHttpClient ();
        HttpResponse<String> getResponse = httpClient.send (getRequest, HttpResponse.BodyHandlers.ofString ());
        System.out.println ("Request status: " + getResponse.statusCode ());

        Gson gson = new Gson ();
        Transcript transcript = gson.fromJson (getResponse.body (), Transcript.class);

        //Logging
        System.out.println ("Item count: " + transcript.getResult ().getCount ());
        System.out.println("****************************************************");

        return transcript;

    }
}
