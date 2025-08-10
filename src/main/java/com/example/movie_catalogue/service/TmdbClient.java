package com.example.movie_catalogue.service;

import com.example.movie_catalogue.dto.MovieDetail;
import com.example.movie_catalogue.dto.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TmdbClient {

    private final WebClient webClient;
    private final String apiKey;
    private final String imageBase;

    public TmdbClient(
            WebClient tmdbWebClient,
            @Value("${tmdb.api.key:dummy}") String apiKey,
            @Value("${tmdb.image.base:https://image.tmdb.org/t/p/w500}") String imageBase) {
        this.webClient = tmdbWebClient;
        this.apiKey = apiKey;
        this.imageBase = imageBase.endsWith("/") ? imageBase.substring(0, imageBase.length() - 1) : imageBase;
    }

    /** Trending movies (weekly) */
    public MovieSearchResponse trending() {
        return webClient.get()
                .uri(uri -> uri.path("/trending/movie/week")
                        .queryParam("language", "en-US")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchResponse.class)
                .block();
    }

    /** Search movies by title */
    public MovieSearchResponse search(String query) {
        return webClient.get()
                .uri(uri -> uri.path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("include_adult", false)
                        .queryParam("language", "en-US")
                        .queryParam("page", 1)
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchResponse.class)
                .block();
    }

    /** Single movie details */
    public MovieDetail movieDetails(int id) {
        return webClient.get()
                .uri(uri -> uri.path("/movie/{id}")
                        .queryParam("language", "en-US")
                        .queryParam("api_key", apiKey)
                        .build(id))
                .retrieve()
                .bodyToMono(MovieDetail.class)
                .block();
    }

    /** Build full TMDb image URL from poster/backdrop/profile path */
    public String fullPosterUrl(String path) {
        if (path == null || path.isBlank())
            return null;
        return imageBase + path;
    }
}
