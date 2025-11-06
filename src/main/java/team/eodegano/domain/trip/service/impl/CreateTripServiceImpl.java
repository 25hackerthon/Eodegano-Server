package team.eodegano.domain.trip.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.request.TripRequest;
import team.eodegano.domain.trip.presentation.dto.response.TripResponse;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.domain.trip.service.CreateTripService;

@Service
@RequiredArgsConstructor
public class CreateTripServiceImpl implements CreateTripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public TripResponse execute(TripRequest req) {
        if (req.endDate().isBefore(req.startDate())) {
            throw new IllegalArgumentException("endDate must be after startDate");
        }

        Trip trip = Trip.builder()
                .region(req.region())
                .area(req.area())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .person(req.person())
                .category(req.category())
                .ride(req.ride())
                .build();

        Trip saved = tripRepository.save(trip);

        return new TripResponse(
                saved.getId(),
                saved.getRegion(),
                saved.getArea(),
                saved.getStartDate(),
                saved.getEndDate(),
                saved.getPerson(),
                saved.getCategory(),
                saved.getRide()
        );
    }
}