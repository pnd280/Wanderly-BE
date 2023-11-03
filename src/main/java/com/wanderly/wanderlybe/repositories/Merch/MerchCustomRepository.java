package com.wanderly.wanderlybe.repositories.Merch;

import com.wanderly.wanderlybe.entities.Merch;

import java.util.List;
import java.util.Optional;

public interface MerchCustomRepository {

  List<Merch> findALl();

  List<Merch> findWithPagination(int limit, int offset);

  Optional<Merch> findById(int id);

  void create(Merch merch);

  void updateById(int id, Merch merch);

  void deleteById(int id);

}
