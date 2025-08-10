package com.example.movie_catalogue.controller;

import com.example.movie_catalogue.service.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FavoriteController {

    private final FavoriteService favorites;

    public FavoriteController(FavoriteService favorites) {
        this.favorites = favorites;
    }

    /** List all favorites */
    @GetMapping("/favorites")
    public String list(Model model) {
        model.addAttribute("pageTitle", "Your Favorites");
        model.addAttribute("favorites", favorites.listAll());
        return "favorites";
    }

    /** Add from list or detail views (form POST) */
    @PostMapping("/favorites/add")
    public String add(
            @RequestParam("tmdbId") int tmdbId,
            @RequestParam("title") String title,
            @RequestParam(value = "posterPath", required = false) String posterPath,
            @RequestParam(value = "releaseDate", required = false) String releaseDate,
            @RequestParam(value = "rating", required = false) Double rating) {
        favorites.addIfAbsent(tmdbId, title, posterPath, releaseDate, rating);
        return "redirect:/favorites";
    }

    /** Remove by tmdbId (form POST) */
    @PostMapping("/favorites/remove")
    public String remove(@RequestParam("tmdbId") int tmdbId) {
        favorites.removeByTmdbId(tmdbId);
        return "redirect:/favorites";
    }
}
