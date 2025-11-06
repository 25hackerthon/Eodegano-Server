package team.eodegano.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.eodegano.place.dto.PlaceRequest;
import team.eodegano.place.dto.PlaceResponse;
import team.eodegano.place.entity.Place;
import team.eodegano.place.repository.PlaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse addPlace(Long tripId, PlaceRequest request) {
        Place place = new Place();
        place.setTripId(tripId);
        place.setName(request.getName());
        place.setCategory(request.getCategory());
        place.setAddress(request.getAddress());
        place.setLatitude(request.getLatitude());
        place.setLongitude(request.getLongitude());
        place.setDescription(request.getDescription());
        place.setOrderIndex(request.getOrder() != null ? request.getOrder() : 0);

        Place saved = placeRepository.save(place);
        return toResponse(saved);
    }

    public List<PlaceResponse> getPlaces(Long tripId) {
        return placeRepository.findAllByTripIdOrderByOrderIndex(tripId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PlaceResponse updatePlace(Long tripId, Long placeId, PlaceRequest request) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 place ID"));
        if (!place.getTripId().equals(tripId)) {
            throw new IllegalArgumentException("유효하지 않은 trip ID");
        }

        if (request.getName() != null) place.setName(request.getName());
        if (request.getCategory() != null) place.setCategory(request.getCategory());
        if (request.getAddress() != null) place.setAddress(request.getAddress());
        if (request.getLatitude() != null) place.setLatitude(request.getLatitude());
        if (request.getLongitude() != null) place.setLongitude(request.getLongitude());
        if (request.getDescription() != null) place.setDescription(request.getDescription());
        if (request.getOrder() != null) place.setOrderIndex(request.getOrder());

        Place updated = placeRepository.save(place);
        return toResponse(updated);
    }

    public void deletePlace(Long tripId, Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 place ID"));
        if (!place.getTripId().equals(tripId)) {
            throw new IllegalArgumentException("유효하지 않은 trip ID");
        }
        placeRepository.delete(place);
    }

    public void deleteAllPlaces(Long tripId) {
        placeRepository.deleteAllByTripId(tripId);
    }

    private PlaceResponse toResponse(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getTripId(),
                place.getName(),
                place.getCategory(),
                place.getAddress(),
                place.getLatitude(),
                place.getLongitude(),
                place.getDescription(),
                place.getOrderIndex()
        );
    }
}
