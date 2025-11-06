package team.eodegano.domain.trip.service;

import team.eodegano.domain.trip.presentation.dto.response.FinalTripResponse;

public interface GetFinalTripService {
    FinalTripResponse execute(Long tripId);
}
