package team.eodegano.domain.place.presentation.dto.response;

import team.eodegano.domain.place.entity.Place;

public record PlaceResponse(
        Long id,
        Long tripId,
        String name,
        String category,
        String address,
        Double latitude,
        Double longitude,
        String description,
        Integer orderIndex
) {
    public static PlaceResponse from(Place place) {
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