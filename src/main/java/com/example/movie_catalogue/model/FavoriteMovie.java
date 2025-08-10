package com.example.movie_catalogue.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class FavoriteMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** TMDb numeric movie id (unique in our app) */
    @Column(nullable = false, unique = true)
    private Integer tmdbId;

    @Column(nullable = false)
    private String title;

    private String posterPath;
    private String releaseDate; // keep as String from TMDb (yyyy-MM-dd)
    private Double rating; // vote_average

    public FavoriteMovie() {
    }

    public FavoriteMovie(Integer tmdbId, String title, String posterPath, String releaseDate, Double rating) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    // Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
