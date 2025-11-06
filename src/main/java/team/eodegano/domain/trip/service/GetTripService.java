package team.eodegano.domain.trip.service;

import team.eodegano.domain.trip.presentation.dto.response.TripResponse;

public interface GetTripService {
    TripResponse execute(Long tripId);
}
