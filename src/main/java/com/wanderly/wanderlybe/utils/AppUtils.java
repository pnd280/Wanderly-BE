package com.wanderly.wanderlybe.utils;

import com.wanderly.wanderlybe.entities.Tour;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppUtils {
  public static Map<String, String> SuccessResponse(String message) {
    return Map.of("message", message);
  }

  public static Map<String, String> ErrorResponse(String message) {
    return Map.of("message", message);
  }

  public static Map<String, Object> ErrorResponse(String message, Object data) {
    return Map.of("message", message, "data", data);
  }

  public static Object TourResponse(Tour tour) {
    Map<String, Object> transformedTour = new HashMap<>();

    transformedTour.put("id", tour.getId());
    transformedTour.put("name", tour.getName());
    transformedTour.put("image_src", tour.getImgSrc());
    transformedTour.put("description", tour.getDescription());
    transformedTour.put("duration", tour.getDuration());
    transformedTour.put("guides", tour.getGuides());
    transformedTour.put("accommodations", tour.getAccommodations());
    transformedTour.put("min", tour.getMin());
    transformedTour.put("max", tour.getMax());
    transformedTour.put("pricePerPerson", tour.getPricePerPerson());
    transformedTour.put("merchId", tour.getMerch() != null ? tour.getMerch().getId() : null);

    return transformedTour;
  }

  public static Object ToursResponse(List<Tour> tours) {
    return tours.stream().map(AppUtils::TourResponse);
  }


  public static String saveBase64ImageToFile(String imgBase64) throws IOException {
    String[] imgData = imgBase64.split(",");

    if (imgData.length != 2) return "";

    String metaData = imgData[0];
    String base64String = imgData[1];
    String imgExtension = metaData.split("/")[1].split(";")[0];

    if (imgExtension.isEmpty()) return "";

    byte[] decodedBytes = Base64.getDecoder().decode(base64String);

    String fileName = "image_" + System.currentTimeMillis() + "." + imgExtension;

    Path imagesDirectory = Paths.get(StaticPath.PATH);

    if (!Files.exists(imagesDirectory)) {
      Files.createDirectories(imagesDirectory);
    }

    Path imagePath = imagesDirectory.resolve(fileName);
    try (FileOutputStream fos = new FileOutputStream(imagePath.toFile())) {
      fos.write(decodedBytes);
    }

    return trimPath(imagePath.toString());
  }

  private static String trimPath(String input) {
    Path inputPath = Paths.get(input);
    Path outputPath = inputPath.subpath(inputPath.getNameCount() - 2, inputPath.getNameCount());
    return "/" + outputPath.toString().replace("\\", "/");
  }

  public static <T> void saveImageIfAvailable(
          T dto,
          Function<T, String> imgBase64Extractor,
          Consumer<String> imgSrcSetter
  ) throws IOException {
    String imgBase64 = imgBase64Extractor.apply(dto);

    if (imgBase64 != null && !imgBase64.isEmpty()) {
      String imgSrc = saveBase64ImageToFile(imgBase64);
      imgSrcSetter.accept(!imgSrc.isEmpty() ? imgSrc : null);
    }
  }

  public static void deleteImageBySrc(String imgSrc) {
    if (imgSrc != null && !imgSrc.isEmpty()) {
      Path imagePath = Paths.get("src/main/resources/static" + imgSrc);
      try {
        Files.delete(imagePath);
      } catch (IOException e) {
        System.out.println("Error deleting image: " + e.getMessage());
      }
    }
  }
}