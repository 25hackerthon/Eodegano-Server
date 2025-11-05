package team.eodegano.domain.place.presentation.dto.request;

public record PlaceRequest(
        String name,
        String category,
        String address,
        Double latitude,
        Double longitude,
        String description,
        Integer orderIndex
) {
}