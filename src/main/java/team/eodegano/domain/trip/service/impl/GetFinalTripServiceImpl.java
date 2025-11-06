package team.eodegano.domain.trip.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.eodegano.domain.place.dto.PlaceResponse;
import team.eodegano.domain.place.entity.Place;
import team.eodegano.domain.place.repository.PlaceRepository;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.response.FinalTripResponse;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.domain.trip.service.GetFinalTripService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFinalTripServiceImpl implements GetFinalTripService {

    private final TripRepository tripRepository;
    private final PlaceRepository placeRepository;

    @Override
    public FinalTripResponse execute(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<Place> places = placeRepository.findAllByTripIdOrderByOrderIndex(tripId);

        List<PlaceResponse> placeResponses = places.stream()
                .map(place -> new PlaceResponse(
                        place.getId(),
                        place.getTripId(),
                        place.getName(),
                        place.getCategory(),
                        place.getAddress(),
                        place.getLatitude(),
                        place.getLongitude(),
                        place.getDescription(),
                        place.getOrderIndex()))
                .collect(Collectors.toList());

        return new FinalTripResponse(
                trip.getId(),
                trip.getRegion(),
                trip.getArea(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getPerson(),
                trip.getCategory(),
                trip.getRide(),
                placeResponses
        );
    }
}
