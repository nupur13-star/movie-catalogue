package com.example.movie_catalogue.controller;

import com.example.movie_catalogue.dto.MovieDetail;
import com.example.movie_catalogue.dto.MovieSearchResponse;
import com.example.movie_catalogue.service.TmdbClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    private final TmdbClient tmdb;

    public MovieController(TmdbClient tmdb) {
        this.tmdb = tmdb;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Trending Movies");
        try {
            MovieSearchResponse res = tmdb.trending();
            model.addAttribute("movies", res != null ? res.results() : null);
        } catch (Exception ex) {
            model.addAttribute("movies", null);
            model.addAttribute("error", "Could not load trending movies.");
        }
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String q, Model model) {
        model.addAttribute("pageTitle", "Results for: " + q);
        model.addAttribute("query", q);
        try {
            MovieSearchResponse res = tmdb.search(q);
            model.addAttribute("movies", res != null ? res.results() : null);
        } catch (Exception ex) {
            model.addAttribute("movies", null);
            model.addAttribute("error", "Search failed.");
        }
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String details(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Movie Details");
        try {
            MovieDetail detail = tmdb.movieDetails(id);
            model.addAttribute("detail", detail);
        } catch (Exception ex) {
            model.addAttribute("detail", null);
            model.addAttribute("error", "Could not load movie details.");
        }
        return "movie-detail";
    }
}
