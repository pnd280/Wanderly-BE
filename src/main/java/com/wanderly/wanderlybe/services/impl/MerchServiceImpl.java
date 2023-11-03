package com.wanderly.wanderlybe.services.impl;

import com.wanderly.wanderlybe.DTOs.MerchDTO;
import com.wanderly.wanderlybe.DTOs.TourDTO;
import com.wanderly.wanderlybe.entities.Merch;
import com.wanderly.wanderlybe.repositories.Merch.MerchCustomRepository;
import com.wanderly.wanderlybe.services.MerchService;
import jakarta.transaction.Transactional;
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
public class MerchServiceImpl implements MerchService {

  private final MerchCustomRepository merchRepository;

  public MerchServiceImpl(MerchCustomRepository merchRepository) {
    this.merchRepository = merchRepository;
  }

  @Override
  public List<Merch> findAllMerch() {
    return merchRepository.findALl();
  }

  @Override
  public List<Merch> findWithPagination(int limit, int offset) {
    return merchRepository.findWithPagination(limit, offset);
  }

  @Override
  public Optional<Merch> findMerchById(int id) {
    return merchRepository.findById(id);
  }

  @Override
  @Transactional
  public void createMerch(Merch merch) {
    merchRepository.create(merch);
  }

  @Override
  @Transactional
  public void updateMerchById(int id, Merch merch) {
    merchRepository.updateById(id, merch);
  }

  @Override
  @Transactional
  public void deleteMerchById(int id) {
    merchRepository.deleteById(id);
  }
}
