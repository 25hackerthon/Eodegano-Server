package team.eodegano.domain.trip.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;
import team.eodegano.domain.trip.service.CreateTripService;
import team.eodegano.domain.trip.service.DeleteTripService;
import team.eodegano.domain.trip.service.GetTripService;
import team.eodegano.domain.trip.service.UpdateTripService;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final CreateTripService createTripService;
    private final GetTripService getTripService;
    private final UpdateTripService updateTripService;
    private final DeleteTripService deleteTripService;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest request) {
        TripResponse response = createTripService.execute(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{trip_id}")
    public ResponseEntity<TripResponse> getTrip(@PathVariable("trip_id") Long tripId) {
        TripResponse response = getTripService.execute(tripId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{trip_id}")
    public ResponseEntity<TripResponse> patchTrip(
            @PathVariable Long tripId,
            @RequestBody TripRequest request
    ) {
        TripResponse response = updateTripService.execute(tripId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{trip_id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        deleteTripService.execute(tripId);
        return ResponseEntity.noContent().build();
    }
}