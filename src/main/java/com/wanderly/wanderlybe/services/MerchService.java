package com.wanderly.wanderlybe.services;

import com.wanderly.wanderlybe.DTOs.MerchDTO;
import com.wanderly.wanderlybe.entities.Merch;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface MerchService {


  List<Merch> findAllMerch();

  List<Merch> findWithPagination(int limit, int offset);

  Optional<Merch> findMerchById(int id);

  void createMerch(Merch merch);

  void updateMerchById(int id, Merch merch);

  void deleteMerchById(int id);
}
