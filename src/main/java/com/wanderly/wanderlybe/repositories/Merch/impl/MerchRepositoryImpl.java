package com.wanderly.wanderlybe.repositories.Merch.impl;

import com.wanderly.wanderlybe.entities.Merch;
import com.wanderly.wanderlybe.repositories.Merch.MerchCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MerchRepositoryImpl implements MerchCustomRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Merch> findALl() {
    return entityManager.createQuery("SELECT m FROM Merch m", Merch.class).getResultList();
  }

  @Override
  public List<Merch> findWithPagination(int limit, int offset) {
    TypedQuery<Merch> query = entityManager.createQuery("SELECT m FROM Merch m", Merch.class);
    query.setFirstResult(offset);
    query.setMaxResults(limit);

    return query.getResultList();
  }

  @Override
  public Optional<Merch> findById(int id) {
    return Optional.ofNullable(entityManager.find(Merch.class, id));
  }

  @Override
  public void create(Merch merch) {
    entityManager.persist(merch);
  }

  @Override
  public void updateById(int id, Merch merch) {
    Merch merchToUpdate = entityManager.find(Merch.class, id);

    merchToUpdate.setName(merch.getName());
    if (merch.getImgSrc() != null) merchToUpdate.setImgSrc(merch.getImgSrc());
    merchToUpdate.setIsPopular(merch.getIsPopular());
    merchToUpdate.setPrice(merch.getPrice());
  }

  @Override
  public void deleteById(int id) {
    Merch merchToDelete = entityManager.find(Merch.class, id);
    entityManager.remove(merchToDelete);
  }
}
