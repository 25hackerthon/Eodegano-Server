package team.eodegano.domain.trip.service;

import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;

public interface UpdateTripService {
    TripResponse execute(Long tripId, TripRequest request);

}
