package org.leanpoker.player.rank;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import org.leanpoker.player.GameState;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.http.uri.UriBuilder;

public class RankClient {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();
    private static ObjectMapper mapper = new ObjectMapper();

    public static RankResponse getRank(List<GameState.HoleCard> listOfCards) {
        try {
            String cards = mapper.writeValueAsString(listOfCards);
            var url = UriBuilder
                    .of("http://rainman.leanpoker.org/rank")
                    .queryParam("cards", cards).build().toURL();

            HttpRequest build = HttpRequest.newBuilder()
                    .uri(url.toURI())
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), RankResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
