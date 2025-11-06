package team.eodegano.domain.trip.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.domain.trip.service.GetTripService;

@Service
@RequiredArgsConstructor
public class GetTripServiceImpl implements GetTripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional(readOnly = true)
    public TripResponse execute(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        return new TripResponse(
                trip.getId(),
                trip.getRegion(),
                trip.getArea(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getPerson(),
                trip.getCategory(),
                trip.getRide()
        );
    }
}