package com.example.mooviesapi.controllers;

import com.example.mooviesapi.models.Feedback;
import com.example.mooviesapi.models.Movie;
import com.example.mooviesapi.models.MovieList;
import com.example.mooviesapi.repositories.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
        // this gives us the total moves count in our database
        int moviesCount = (int)moviesRepo.count();

        // this is the integer (whole number) that represents
        // the page size that we're interested in viewing
        int pageSize = 20;

        // total number of pages is the total movie count
        // divided by how many movies are shown per page
        int pages = (int)Math.ceil((double)moviesCount / pageSize);

        int randomPage = (int)(Math.random() * pages);

        return moviesRepo.findAll(new PageRequest(randomPage, pageSize)).getContent();
//        return moviesRepo.findAll(new PageRequest(0, 20)).getContent();
    }

    @CrossOrigin
    @RequestMapping(path="/rating", method = RequestMethod.POST)
    public void giveMovieFeedback(@RequestBody Feedback fb) {
        System.out.printf("%s added some feedback: they did %s like %s\n",
            fb.getName(),
            fb.isLiked() ? "" : "not",
            moviesRepo.findOne(fb.getMovieId()).getTitle());
    }
}
