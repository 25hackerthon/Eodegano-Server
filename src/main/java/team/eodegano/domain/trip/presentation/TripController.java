package team.eodegano.domain.trip.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.FinalTripResponse;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;
import team.eodegano.domain.trip.service.*;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final CreateTripService createTripService;
    private final GetTripService getTripService;
    private final UpdateTripService updateTripService;
    private final DeleteTripService deleteTripService;
    private final GetFinalTripService getFinalTripService;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@Valid @RequestBody TripRequest request) {
        return ResponseEntity.ok(createTripService.execute(request));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResponse> getTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(getTripService.execute(tripId));
    }

    @GetMapping("/{tripId}/final")
    public ResponseEntity<FinalTripResponse> getFinalTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(getFinalTripService.execute(tripId));
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponse> patchTrip(
            @PathVariable Long tripId,
            @RequestBody TripRequest request
    ) {
        return ResponseEntity.ok(updateTripService.execute(tripId, request));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        deleteTripService.execute(tripId);
        return ResponseEntity.noContent().build();
    }
}