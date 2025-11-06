package team.eodegano.domain.trip.service;

import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;

public interface CreateTripService {
    TripResponse execute(TripRequest request);
}
