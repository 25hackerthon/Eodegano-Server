package team.eodegano.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.place.entity.Place;
import team.eodegano.domain.place.presentation.dto.request.PlaceRequest;
import team.eodegano.domain.place.presentation.dto.response.PlaceResponse;
import team.eodegano.domain.place.repository.PlaceRepository;
import team.eodegano.global.exception.PlaceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse addPlace(Long tripId, PlaceRequest request) {
        Integer orderIndex = request.orderIndex();

        if (orderIndex != null) {
            placeRepository.incrementOrderIndexFrom(tripId, orderIndex);
        } else {
            orderIndex = placeRepository.findTopByTripIdOrderByOrderIndexDesc(tripId)
                    .map(p -> p.getOrderIndex() + 1)
                    .orElse(0);
        }

        Place place = Place.builder()
                .tripId(tripId)
                .name(request.name())
                .category(request.category())
                .address(request.address())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .description(request.description())
                .orderIndex(orderIndex)
                .build();
        return PlaceResponse.from(placeRepository.save(place));
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> getPlaces(Long tripId) {
        return placeRepository.findAllByTripIdOrderByOrderIndex(tripId)
                .stream()
                .map(PlaceResponse::from)
                .toList();
    }

    public PlaceResponse updatePlace(Long tripId, Long placeId, PlaceRequest request) {
        Place place = findPlaceByIdAndTripId(placeId, tripId);
        place.update(request.name(), request.category(), request.address(),
                request.latitude(), request.longitude(), request.description(), request.orderIndex());
        return PlaceResponse.from(place);
    }

    public void deletePlace(Long tripId, Long placeId) {
        Place place = findPlaceByIdAndTripId(placeId, tripId);
        placeRepository.delete(place);
    }

    public void deleteAllPlaces(Long tripId) {
        placeRepository.deleteAllByTripId(tripId);
    }

    private Place findPlaceByIdAndTripId(Long placeId, Long tripId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
        if (!place.getTripId().equals(tripId)) {
            throw new PlaceNotFoundException(placeId);
        }
        return place;
    }
}