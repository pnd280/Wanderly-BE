package com.wanderly.wanderlybe.controllers;

import com.wanderly.wanderlybe.DTOs.TourDTO;
import com.wanderly.wanderlybe.entities.Merch;
import com.wanderly.wanderlybe.entities.Tour;
import com.wanderly.wanderlybe.services.MerchService;
import com.wanderly.wanderlybe.services.TourService;
import com.wanderly.wanderlybe.utils.AppUtils;
import com.wanderly.wanderlybe.exceptions.ApiException;
import com.wanderly.wanderlybe.utils.StaticPath;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "http://localhost:5173")
public class TourController {

  private final TourService tourService;
  private final MerchService merchService;

  public TourController(TourService tourService, MerchService merchService) {
    this.tourService = tourService;
    this.merchService = merchService;
  }

  @GetMapping("/test")
  public ResponseEntity<Object> test() {
    return ResponseEntity.ok(Map.of("message", Paths.get(StaticPath.PATH).toUri().toString()));
  }

  @GetMapping("/all")
  public ResponseEntity<Object> getAllTours(@RequestParam(value = "count", required = false) Optional<Boolean> countOnly) {
    List<Tour> tours = tourService.findAllTours();

    if (countOnly.isPresent() && countOnly.get()) {
      return ResponseEntity.ok(Map.of("count", tours.size()));
    } else {
      return ResponseEntity.ok(AppUtils.ToursResponse(tours));
    }
  }

  @GetMapping("")
  public ResponseEntity<List<Tour>> getTours(@RequestParam int limit, @RequestParam int offset) {
    return ResponseEntity.ok(tourService.findWithPagination(limit, offset));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getTourById(@PathVariable int id) {
    Optional<Tour> tour = tourService.findTourById(id);
    if (tour.isPresent()) {
      return ResponseEntity.ok(AppUtils.TourResponse(tour.get()));
    } else {
      throw new ApiException("Tour with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createTour(@Valid @RequestBody TourDTO tour) throws IOException {
    AppUtils.saveImageIfAvailable(tour, t -> t.getDetails().getImgBase64(), imgSrc -> tour.getDetails().setImgSrc(imgSrc));

    Optional<Merch> freeMerch = merchService.findMerchById(tour.getMerchId());

    Tour newTour = tour.toTour(freeMerch.orElse(null));

    tourService.createTour(newTour);
    return ResponseEntity.status(HttpStatus.CREATED).body(AppUtils.TourResponse(newTour));
  }

  @PutMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ResponseEntity<Object> updateTour(@PathVariable int id, @Valid @RequestBody TourDTO tour) throws IOException {
    Optional<Tour> tourToUpdate = tourService.findTourById(id);
    Optional<Merch> freeMerch = merchService.findMerchById(tour.getMerchId());


    if (tourToUpdate.isPresent()) {
      AppUtils.saveImageIfAvailable(tour, t -> t.getDetails().getImgBase64(), imgSrc -> tour.getDetails().setImgSrc(imgSrc));
      tourService.updateTourById(id, tour.toTour(freeMerch.orElse(null)));
      return ResponseEntity.ok(AppUtils.SuccessResponse("Tour with id " + id + " updated successfully!"));
    } else {

      throw new ApiException("Tour with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTour(@PathVariable int id) {
    Optional<Tour> tourToDelete = tourService.findTourById(id);
    if (tourToDelete.isPresent()) {
      AppUtils.deleteImageBySrc(tourToDelete.get().getImgSrc());
      tourService.deleteTourById(id);
      return ResponseEntity.ok(AppUtils.SuccessResponse("Tour with id " + id + " deleted successfully!"));
    } else {
      throw new ApiException("Tour with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }
}