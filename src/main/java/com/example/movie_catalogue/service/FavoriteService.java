package com.example.movie_catalogue.service;

import com.example.movie_catalogue.model.FavoriteMovie;
import com.example.movie_catalogue.repo.FavoriteMovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteMovieRepository repo;

    public FavoriteService(FavoriteMovieRepository repo) {
        this.repo = repo;
    }

    public List<FavoriteMovie> listAll() {
        return repo.findAll();
    }

    public boolean isFavorite(int tmdbId) {
        return repo.existsByTmdbId(tmdbId);
    }

    @Transactional
    public void addIfAbsent(int tmdbId, String title, String posterPath, String releaseDate, Double rating) {
        if (repo.existsByTmdbId(tmdbId))
            return;
        FavoriteMovie fav = new FavoriteMovie(tmdbId, title, posterPath, releaseDate, rating);
        repo.save(fav);
    }

    @Transactional
    public void removeByTmdbId(int tmdbId) {
        repo.deleteByTmdbId(tmdbId);
    }
}
