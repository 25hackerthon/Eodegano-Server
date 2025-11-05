package team.eodegano.domain.place.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.eodegano.domain.place.presentation.dto.request.PlaceRequest;
import team.eodegano.domain.place.presentation.dto.response.PlaceResponse;
import team.eodegano.domain.place.service.PlaceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips/{tripId}/places")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceResponse> addPlace(
            @PathVariable Long tripId,
            @RequestBody PlaceRequest request
    ) {
        return ResponseEntity.ok(placeService.addPlace(tripId, request));
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getPlaces(@PathVariable Long tripId) {
        return ResponseEntity.ok(placeService.getPlaces(tripId));
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity<PlaceResponse> updatePlace(
            @PathVariable Long tripId,
            @PathVariable Long placeId,
            @RequestBody PlaceRequest request
    ) {
        return ResponseEntity.ok(placeService.updatePlace(tripId, placeId, request));
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> deletePlace(
            @PathVariable Long tripId,
            @PathVariable Long placeId
    ) {
        placeService.deletePlace(tripId, placeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPlaces(@PathVariable Long tripId) {
        placeService.deleteAllPlaces(tripId);
        return ResponseEntity.noContent().build();
    }
}
