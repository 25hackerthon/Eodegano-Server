package team.eodegano.domain.trip.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecommendationResponse {
    private String recommendedPlaces;
}
