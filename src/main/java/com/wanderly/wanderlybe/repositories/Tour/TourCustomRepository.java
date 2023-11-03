package com.wanderly.wanderlybe.repositories.Tour;

import com.wanderly.wanderlybe.entities.Tour;

import java.util.List;
import java.util.Optional;


public interface TourCustomRepository {

  List<Tour> findAll();

  List<Tour> findWithPagination(int limit, int offset);

  Optional<Tour> findById(int id);

  void create(Tour tour);

  void updateById(int id, Tour tour);

  void deleteById(int id);
}
