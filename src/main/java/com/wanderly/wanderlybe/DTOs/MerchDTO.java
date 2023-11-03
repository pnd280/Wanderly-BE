package com.wanderly.wanderlybe.DTOs;

import com.wanderly.wanderlybe.entities.Merch;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class MerchDTO {

  @NotNull
  @Length(min = 3, max = 100)
  private String name;

  @NotNull
  @Min(1)
  private int price;

  @NotNull
  private Boolean isPopular;

  @Nullable
  private String imgSrc;

  @Nullable
  private String imgBase64;

  public Merch toMerch() {
    return new Merch(name, price, isPopular, imgSrc);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Boolean getIsPopular() {
    return isPopular;
  }

  public void setIsPopular(Boolean popular) {
    isPopular = popular;
  }

  @Nullable
  public String getImgSrc() {
    return imgSrc;
  }

  public void setImgSrc(@Nullable String imgSrc) {
    this.imgSrc = imgSrc;
  }

  @Nullable
  public String getImgBase64() {
    return imgBase64;
  }

  public void setImgBase64(@Nullable String imgBase64) {
    this.imgBase64 = imgBase64;
  }
}
