package com.wanderly.wanderlybe.repositories.Tour.impl;

import com.wanderly.wanderlybe.entities.Tour;
import com.wanderly.wanderlybe.repositories.Tour.TourCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TourRepositoryImpl implements TourCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Tour> findAll() {
    return entityManager.createQuery("SELECT t FROM Tour t", Tour.class).getResultList();
  }

  @Override
  public List<Tour> findWithPagination(int limit, int offset) {
    TypedQuery<Tour> query = entityManager.createQuery("SELECT t FROM Tour t", Tour.class);
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    return query.getResultList();
  }

  @Override
  public Optional<Tour> findById(int id) {
    return Optional.ofNullable(entityManager.find(Tour.class, id));
  }

  @Override
  public void create(Tour tour) {
    entityManager.persist(tour);
  }

  @Override
  public void updateById(int id, Tour tour) {
    Tour tourToUpdate = entityManager.find(Tour.class, id);

    tourToUpdate.setName(tour.getName());
    if (tour.getImgSrc() != null) tourToUpdate.setImgSrc(tour.getImgSrc());
    tourToUpdate.setDescription(tour.getDescription());
    tourToUpdate.setDuration(tour.getDuration());
    tourToUpdate.setGuides(tour.getGuides());
    tourToUpdate.setAccommodations(tour.getAccommodations());
    tourToUpdate.setMin(tour.getMin());
    tourToUpdate.setMax(tour.getMax());
    tourToUpdate.setPricePerPerson(tour.getPricePerPerson());
    tourToUpdate.setMerch(tour.getMerch());

    entityManager.merge(tourToUpdate);
  }

  @Override
  public void deleteById(int id) {
    Tour tourToDelete = entityManager.find(Tour.class, id);
    entityManager.remove(tourToDelete);
  }

}
