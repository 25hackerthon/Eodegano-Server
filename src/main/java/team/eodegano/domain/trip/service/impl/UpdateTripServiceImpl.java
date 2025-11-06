package team.eodegano.domain.trip.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.domain.trip.service.UpdateTripService;

@Service
@RequiredArgsConstructor
public class UpdateTripServiceImpl implements UpdateTripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public TripResponse execute(Long tripId, TripRequest req) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        trip.update(
                req.region(),
                req.area(),
                req.startDate(),
                req.endDate(),
                req.person(),
                req.category(),
                req.ride()
        );

        Trip updated = tripRepository.save(trip);

        return new TripResponse(
                updated.getId(),
                updated.getRegion(),
                updated.getArea(),
                updated.getStartDate(),
                updated.getEndDate(),
                updated.getPerson(),
                updated.getCategory(),
                updated.getRide()
        );
    }
}