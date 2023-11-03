package com.wanderly.wanderlybe.repositories.Merch;

import com.wanderly.wanderlybe.entities.Merch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchRepository extends JpaRepository<Merch, Integer>, MerchCustomRepository {
}
