package com.wanderly.wanderlybe.services;

import com.wanderly.wanderlybe.DTOs.TourDTO;
import com.wanderly.wanderlybe.entities.Tour;
import com.wanderly.wanderlybe.repositories.Tour.TourCustomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> findAllTours();

    List<Tour> findWithPagination(int limit, int offset);

    Optional<Tour> findTourById(int id);

    void createTour(Tour tour);

    void updateTourById(int id, Tour tour);

    void deleteTourById(int id);
}