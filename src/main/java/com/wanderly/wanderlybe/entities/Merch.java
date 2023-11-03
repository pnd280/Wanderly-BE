package com.wanderly.wanderlybe.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "merchs")
public class Merch {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private int price;

  @Column(name = "is_popular")
  private Boolean isPopular;

  @Column(name = "image_src")
  private String image_src;

  @OneToMany(mappedBy = "merch")
  private Set<Tour> tour;

  public Merch() {
  }

  public Merch(String name, int price, Boolean isPopular, String img_src) {
    setName(name);
    setPrice(price);
    setIsPopular(isPopular);
    setImgSrc(img_src);
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public Boolean getIsPopular() {
    return isPopular;
  }

  public String getImgSrc() {
    return image_src;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setIsPopular(Boolean isPopular) {
    this.isPopular = isPopular;
  }

  public void setImgSrc(String img_src) {
    this.image_src = img_src;
  }

  @Override
  public String toString() {
    return "Merch{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", isPopular=" + isPopular +
            ", imgSrc='" + image_src + '\'' +
            '}';
  }
}
