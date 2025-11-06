package team.eodegano.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.place.dto.PlaceRequest;
import team.eodegano.domain.place.dto.PlaceResponse;
import team.eodegano.domain.place.entity.Place;
import team.eodegano.domain.place.repository.PlaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceResponse addPlace(Long tripId, PlaceRequest request) {
        Integer orderIndex = request.getOrder();

        if (orderIndex != null) {
            placeRepository.incrementOrderIndexFrom(tripId, orderIndex);
        } else {
            orderIndex = placeRepository.findTopByTripIdOrderByOrderIndexDesc(tripId)
                    .map(p -> p.getOrderIndex() + 1)
                    .orElse(0);
        }

        Place place = new Place();
        place.setTripId(tripId);
        place.setName(request.getName());
        place.setCategory(request.getCategory());
        place.setAddress(request.getAddress());
        place.setLatitude(request.getLatitude());
        place.setLongitude(request.getLongitude());
        place.setDescription(request.getDescription());
        place.setOrderIndex(orderIndex);

        Place saved = placeRepository.save(place);
        return toResponse(saved);
    }

    public List<PlaceResponse> getPlaces(Long tripId) {
        return placeRepository.findAllByTripIdOrderByOrderIndex(tripId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deletePlace(Long tripId, Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 place ID"));
        if (!place.getTripId().equals(tripId)) {
            throw new IllegalArgumentException("유효하지 않은 trip ID");
        }
        placeRepository.delete(place);
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
