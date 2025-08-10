package com.example.movie_catalogue.dto;

import java.util.List;

public record MovieSearchResponse(
        int page,
        List<MovieSummary> results,
        int total_pages,
        int total_results) {
}
