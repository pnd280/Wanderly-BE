package com.wanderly.wanderlybe.DTOs;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class TourDetailsDTO {

  @NotNull
  @Length(min = 3)
  private String description;

  @Nullable
  private String imgBase64;

  @Nullable
  private String imgSrc;

  @NotNull
  @Min(1)
  @Max(30)
  private int duration;

  @NotNull
  @Min(1)
  @Max(10)
  private int guides;

  @NotNull
  @Min(1)
  private int min;

  @NotNull
  @Min(1)
  private int max;

  @NotNull
  @Length(min = 3, max = 100)
  private String accommodations;


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Nullable
  public String getImgBase64() {
    return imgBase64;
  }

  public void setImgBase64(@Nullable String imgBase64) {
    this.imgBase64 = imgBase64;
  }

  @Nullable
  public String getImgSrc() {
    return imgSrc;
  }

  public void setImgSrc(@Nullable String imgSrc) {
    this.imgSrc = imgSrc;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getGuides() {
    return guides;
  }

  public void setGuides(int guides) {
    this.guides = guides;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public String getAccommodations() {
    return accommodations;
  }

  public void setAccommodations(String accommodations) {
    this.accommodations = accommodations;
  }
}
