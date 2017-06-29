package com.example.mooviesapi.repositories;

import com.example.mooviesapi.models.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
}
