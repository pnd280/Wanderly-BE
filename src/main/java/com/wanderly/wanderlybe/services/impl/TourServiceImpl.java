package com.wanderly.wanderlybe.services.impl;

import com.wanderly.wanderlybe.DTOs.TourDTO;
import com.wanderly.wanderlybe.entities.Tour;
import com.wanderly.wanderlybe.repositories.Tour.TourCustomRepository;
import com.wanderly.wanderlybe.services.TourService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
  private final TourCustomRepository tourRepository;

  public TourServiceImpl(TourCustomRepository tourRepository) {
    this.tourRepository = tourRepository;
  }

  @Override
  public List<Tour> findAllTours() {
    return tourRepository.findAll();
  }

  @Override
  public List<Tour> findWithPagination(int limit, int offset) {
    return tourRepository.findWithPagination(limit, offset);
  }

  @Override
  public Optional<Tour> findTourById(int id) {
    return tourRepository.findById(id);
  }

  @Override
  @Transactional
  public void createTour(Tour tour) {
    tourRepository.create(tour);
  }

  @Override
  @Transactional
  public void updateTourById(int id, Tour tour) {
    tourRepository.updateById(id, tour);
  }

  @Override
  @Transactional
  public void deleteTourById(int id) {
    tourRepository.deleteById(id);
  }
}
