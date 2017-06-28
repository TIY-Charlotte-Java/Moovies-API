package com.example.mooviesapi.controllers;

import com.example.mooviesapi.models.Movie;
import com.example.mooviesapi.models.MovieList;
import com.example.mooviesapi.repositories.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
public class MooviesController {
    @Autowired
    MovieRepository moviesRepo;

    @PostConstruct
    public void init() throws IOException {
        // only run this code when there are no entries
        // already in the database.
        if (moviesRepo.count() == 0) {
            // The object that turns the json into
            // java objects
            ObjectMapper mapper = new ObjectMapper();

            // f is the object that holds the file data
            File f = new File("moovies.json");


            // fileScanner is going to read from the file
            Scanner fileScanner = new Scanner(f);
            fileScanner.useDelimiter("\\Z");

            MovieList movies = mapper.readValue(fileScanner.next(), MovieList.class);

            // save our movies into our database
            moviesRepo.save(movies);
        }
    }

    @CrossOrigin
    @RequestMapping(path="/movies", method = RequestMethod.GET)
    public List<Movie> getRandomMovies() {
        return (List)moviesRepo.findAll();
    }
}
