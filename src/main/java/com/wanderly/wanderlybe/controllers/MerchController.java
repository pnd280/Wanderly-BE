package com.wanderly.wanderlybe.controllers;

import com.wanderly.wanderlybe.DTOs.MerchDTO;
import com.wanderly.wanderlybe.entities.Merch;
import com.wanderly.wanderlybe.services.MerchService;
import com.wanderly.wanderlybe.utils.AppUtils;
import com.wanderly.wanderlybe.exceptions.ApiException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/merchs")
@CrossOrigin(origins = "http://localhost:5173")
public class MerchController {
  private final MerchService merchService;

  public MerchController(MerchService merchService) {
    this.merchService = merchService;
  }

  @GetMapping("/all")
  public ResponseEntity<Object> getAllMerch(@RequestParam(value = "count", required = false) Optional<Boolean> countOnly) {
    List<Merch> merchs = merchService.findAllMerch();

    if (countOnly.isPresent() && countOnly.get()) {
      return ResponseEntity.ok(Map.of("count", merchs.size()));
    } else {
      return ResponseEntity.ok(merchs);
    }
  }

  @GetMapping("")
  public ResponseEntity<List<Merch>> getTours(@RequestParam int limit, @RequestParam int offset) {
    return ResponseEntity.ok(merchService.findWithPagination(limit, offset));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Merch> getMerchById(@RequestParam int id) {
    Optional<Merch> merch = merchService.findMerchById(id);
    if (merch.isPresent()) {
      return ResponseEntity.ok(merch.get());
    } else {
      throw new ApiException("Merch with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createMerch(@Valid @RequestBody MerchDTO merch) throws IOException {
    AppUtils.saveImageIfAvailable(merch, MerchDTO::getImgBase64, merch::setImgSrc);

    Merch newMerch = merch.toMerch();

    merchService.createMerch(newMerch);
    return ResponseEntity.status(HttpStatus.CREATED).body(newMerch);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateMerchById(@PathVariable int id, @Valid @RequestBody MerchDTO merch) throws IOException {
    Optional<Merch> merchToUpdate = merchService.findMerchById(id);

    if (merchToUpdate.isPresent()) {
      AppUtils.saveImageIfAvailable(merch, MerchDTO::getImgBase64, merch::setImgSrc);

      merchService.updateMerchById(id, merch.toMerch());
      return ResponseEntity.ok(AppUtils.SuccessResponse("Merch updated successfully!"));
    } else {
      throw new ApiException("Merch with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteMerchById(@PathVariable int id) {
    Optional<Merch> merchToDelete = merchService.findMerchById(id);

    if (merchToDelete.isPresent()) {
      merchService.deleteMerchById(id);
      return ResponseEntity.ok(AppUtils.SuccessResponse("Merch with id " + id + " deleted successfully!"));
    } else {
      throw new ApiException("Merch with id " + id + " not found!", HttpStatus.NOT_FOUND);
    }
  }
}
