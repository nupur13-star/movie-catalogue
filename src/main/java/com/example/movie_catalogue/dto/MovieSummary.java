package com.example.movie_catalogue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieSummary(
                int id,
                @JsonProperty("title") String title,
                @JsonProperty("poster_path") String posterPath,
                @JsonProperty("release_date") String releaseDate,
                @JsonProperty("vote_average") Double rating,
                @JsonProperty("overview") String overview) {
}
