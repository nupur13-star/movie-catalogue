package com.example.movie_catalogue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MovieDetail(
        int id,
        String title,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("vote_average") Double rating,
        String overview,
        Integer runtime,
        List<Genre> genres) {
}
