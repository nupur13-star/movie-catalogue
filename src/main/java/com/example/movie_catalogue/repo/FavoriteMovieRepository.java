package com.example.movie_catalogue.repo;

import com.example.movie_catalogue.model.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    boolean existsByTmdbId(Integer tmdbId);

    Optional<FavoriteMovie> findByTmdbId(Integer tmdbId);

    void deleteByTmdbId(Integer tmdbId);
}
