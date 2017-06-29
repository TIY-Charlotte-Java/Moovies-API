package com.example.mooviesapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {
    private String name;
    private boolean liked;
    @JsonProperty("movie_id")
    private int movieId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
