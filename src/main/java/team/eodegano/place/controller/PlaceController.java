package team.eodegano.place.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.eodegano.place.dto.PlaceRequest;
import team.eodegano.place.dto.PlaceResponse;
import team.eodegano.place.service.PlaceService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/{tripId}")
    public ResponseEntity<PlaceResponse> addPlace(
            @PathVariable Long tripId,
            @RequestBody PlaceRequest request
    ) {
        return ResponseEntity.ok(placeService.addPlace(tripId, request));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<List<PlaceResponse>> getPlaces(
            @PathVariable Long tripId
    ) {
        return ResponseEntity.ok(placeService.getPlaces(tripId));
    }

    @PatchMapping("/{tripId}/{placeId}")
    public ResponseEntity<PlaceResponse> updatePlace(
            @PathVariable Long tripId,
            @PathVariable Long placeId,
            @RequestBody PlaceRequest request
    ) {
        return ResponseEntity.ok(placeService.updatePlace(tripId, placeId, request));
    }

    @DeleteMapping("/{tripId}/{placeId}")
    public ResponseEntity<Map<String, Object>> deletePlace(
            @PathVariable Long tripId,
            @PathVariable Long placeId
    ) {
        placeService.deletePlace(tripId, placeId);
        return ResponseEntity.ok(Map.of("message", "장소 삭제 성공"));
    }

    @DeleteMapping("/{tripId}/all")
    public ResponseEntity<Map<String, Object>> deleteAllPlaces(
            @PathVariable Long tripId
    ) {
        placeService.deleteAllPlaces(tripId);
        return ResponseEntity.ok(Map.of("message", "모든 장소 삭제 성공"));
    }
}
