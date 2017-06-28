package com.example.mooviesapi.repositories;

import com.example.mooviesapi.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {


}
