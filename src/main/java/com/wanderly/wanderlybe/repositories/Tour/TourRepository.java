package com.wanderly.wanderlybe.repositories.Tour;

import com.wanderly.wanderlybe.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TourRepository extends JpaRepository<Tour, Integer>, TourCustomRepository {
}