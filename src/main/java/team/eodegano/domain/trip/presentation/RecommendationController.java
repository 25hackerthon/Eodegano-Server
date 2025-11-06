package team.eodegano.domain.trip.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.eodegano.domain.place.dto.PlaceResponse;
import team.eodegano.domain.trip.presentation.dto.request.RecommendationRequest;
import team.eodegano.domain.trip.service.AttractionRecommendationService;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final AttractionRecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<List<PlaceResponse>> getRecommendations(@RequestBody RecommendationRequest request) {
        List<PlaceResponse> response = recommendationService.getRecommendedAttractions(request);
        return ResponseEntity.ok(response);
    }
}
