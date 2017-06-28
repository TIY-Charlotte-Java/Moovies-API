package com.example.mooviesapi.repositories;

import com.example.mooviesapi.models.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {


}
