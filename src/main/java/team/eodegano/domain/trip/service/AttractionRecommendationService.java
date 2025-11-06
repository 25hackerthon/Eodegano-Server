package team.eodegano.domain.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.request.RecommendationRequest;
import team.eodegano.domain.trip.presentation.dto.response.RecommendationResponse;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.global.gpt.GptApiClient;
import team.eodegano.global.gpt.dto.GptMessage;
import team.eodegano.global.gpt.dto.GptRequest;
import team.eodegano.global.gpt.dto.GptResponse;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AttractionRecommendationService {

    private final TripRepository tripRepository;
    private final GptApiClient gptApiClient;

    public RecommendationResponse getRecommendedAttractions(RecommendationRequest request) {
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with ID: " + request.getTripId()));

        String gptPrompt = String.format(
                "사용자의 여행 계획을 바탕으로 명소를 추천해줘. " +
                        "지역: %s, 상세 지역: %s, 시작 날짜: %s, 종료 날짜: %s, 인원수: %d, 카테고리: %s, 이동 수단: %s. " +
                        "이 정보를 바탕으로 사용자에게 적합한 명소를 추천하고 그 이유를 설명해줘. " +
                        "응답은 오직 추천하는 장소 목록만 쉼표로 구분된 문자열로 제공해줘. 다른 설명이나 추가 text는 일절 포함하지 마. 마침표나 입니다 같은 문장 끝맺음은 절대 포함하지 마. 예를 들어, 장소1, 장소2, 장소3" +
                trip.getId(), trip.getRegion(), trip.getArea(), trip.getStartDate(), trip.getEndDate(), trip.getPerson(), trip.getCategory(), trip.getRide()
        );

        GptRequest gptRequest = GptRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(
                        new GptMessage("system", "You are a helpful assistant that recommends travel attractions. Respond only in Korean."),
                        new GptMessage("user", gptPrompt)
                ))
                .build();

        GptResponse gptResponse = gptApiClient.callGptApi(gptRequest);

        String fullGptResponseContent = gptResponse.getChoices().get(0).getMessage().getContent();

        return RecommendationResponse.builder()
                .recommendedPlaces(fullGptResponseContent)
                .build();
    }
}
