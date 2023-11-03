package com.wanderly.wanderlybe.DTOs;

import com.wanderly.wanderlybe.entities.Merch;
import com.wanderly.wanderlybe.entities.Tour;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;

public class TourDTO {
  @NotNull
  @Length(min = 3, max = 100)
  private String name;

  @NotNull
  @Min(1)
  private int pricePerPerson;

  @NotNull
  @Valid
  private TourDetailsDTO details;

  private int merchId;

  public int getMerchId() {
    return merchId;
  }

  public void setMerchId(int merchId) {
    this.merchId = merchId;
  }

  public int getPricePerPerson() {
    return pricePerPerson;
  }

  public void setPricePerPerson(int pricePerPerson) {
    this.pricePerPerson = pricePerPerson;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TourDetailsDTO getDetails() {
    return details;
  }

  public void setDetails(TourDetailsDTO details) {
    this.details = details;
  }

  public Tour toTour(Merch merch) {
    return new Tour(name, details.getImgSrc(), details.getDescription(), details.getDuration(), details.getGuides(), details.getAccommodations(), details.getMin(), details.getMax(), pricePerPerson, merch);
  }
}
