package com.wanderly.wanderlybe.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tours")
public class Tour {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "image_src")
  private String imgSrc;

  @Column(name = "description", length = 1000)
  private String description;


  @Column(name = "duration")
  private int duration;

  @Column(name = "guides")
  private int guides;

  @Column(name = "accommodations")
  private String accommodations;

  @Column(name = "min")
  private int min;

  @Column(name = "max")
  private int max;

  @Column(name = "price_per_person")
  private int pricePerPerson;

  @ManyToOne
  @JoinColumn(name = "merch_id")
  @Nullable
  private Merch merch;

  public Tour() {
  }

  public Tour(String name, String imgSrc, String description, int duration, int guides, String accommodations, int min, int max, int pricePerPerson, @Nullable Merch merch) {
    setName(name);
    setImgSrc(imgSrc);
    setDescription(description);
    setDuration(duration);
    setGuides(guides);
    setAccommodations(accommodations);
    setMin(min);
    setMax(max);
    setPricePerPerson(pricePerPerson);
    setMerch(merch);
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getImgSrc() {
    return imgSrc;
  }

  public String getDescription() {
    return description;
  }

  public int getDuration() {
    return duration;
  }

  public int getGuides() {
    return guides;
  }

  public String getAccommodations() {
    return accommodations;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public int getPricePerPerson() {
    return pricePerPerson;
  }

  @Nullable
  public Merch getMerch() {
    return merch;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setImgSrc(String imgSrc) {
    this.imgSrc = imgSrc;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setGuides(int guides) {
    this.guides = guides;
  }

  public void setAccommodations(String accommodations) {
    this.accommodations = accommodations;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public void setPricePerPerson(int pricePerPerson) {
    this.pricePerPerson = pricePerPerson;
  }

  public void setMerch(@Nullable Merch merch) {
    this.merch = merch;
  }

  @Override
  public String toString() {
    return "Tour{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", image_src='" + imgSrc + '\'' +
      ", description='" + description + '\'' +
      ", duration=" + duration +
      ", guides=" + guides +
      ", accommodations='" + accommodations + '\'' +
      ", min=" + min +
      ", max=" + max +
      ", pricePerPerson=" + pricePerPerson +
      '}';
  }

}
