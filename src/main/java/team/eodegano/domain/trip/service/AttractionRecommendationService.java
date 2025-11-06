package team.eodegano.domain.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.eodegano.domain.place.dto.PlaceResponse;
import team.eodegano.domain.place.entity.Place;
import team.eodegano.domain.place.repository.PlaceRepository;
import team.eodegano.domain.trip.entity.Trip;
import team.eodegano.domain.trip.presentation.dto.request.RecommendationRequest;
import team.eodegano.domain.trip.repository.TripRepository;
import team.eodegano.global.gpt.GptApiClient;
import team.eodegano.global.gpt.dto.GptMessage;
import team.eodegano.global.gpt.dto.GptRequest;
import team.eodegano.global.gpt.dto.GptResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class AttractionRecommendationService {

    private final TripRepository tripRepository;
    private final GptApiClient gptApiClient;
    private final PlaceRepository placeRepository;

    @Transactional
    public List<PlaceResponse> getRecommendedAttractions(RecommendationRequest request) {
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with ID: " + request.getTripId()));

        placeRepository.deleteAllByTripId(trip.getId());

        String gptPrompt = String.format(
                """
                        너는 여행지 추천 전문가야. 아래 조건에 맞는 여행지 목록을 '반드시' 쉼표로 구분해서 응답해야 해.
                        --- 여행 정보 ---
                        1.  여행 지역: %s %s
                        2.  여행 기간: %s ~ %s
                        3.  여행 인원: %d명
                        4.  여행 테마: %s
                        5.  이동 수단: %s
                        
                        --- 절대 규칙 (어기면 안 됨) ---
                        1.  추천 장소는 '반드시' 지정된 '여행 지역' 내에만 있어야 해.
                        2.  장소 이름 '만' 나열해야 해. '아름다운', '역사적인' 같은 수식어나 설명, 부가 정보는 절대 추가하지 마.
                        3.  장소 이름에 지역명(예: '경주')을 중복해서 넣지 마. (예: '경주 불국사' -> '불국사')
                        4.  각 장소 이름 뒤에 마침표(.)나 다른 어떤 특수문자도 붙이지 마.
                        5.  결과는 '오직' 쉼표(,)로만 구분된 목록 형태여야 해. 다른 어떤 문장도 포함해서는 안 돼.
                        6.  만약 추천할 장소가 없거나, 요청을 이해하지 못했다면, '추천 없음' 이라고만 응답해야 해.
                        
                        --- 완벽한 출력 형식 예시 ---
                        불국사,석굴암,첨성대,대릉원,국립경주박물관""",
                trip.getRegion(), trip.getArea(), trip.getStartDate(), trip.getEndDate(), trip.getPerson(), trip.getCategory(), trip.getRide()
        );

        GptRequest gptRequest = GptRequest.builder()
                .model("gpt-4")
                .messages(Arrays.asList(
                        new GptMessage("system", "You are a helpful assistant that recommends travel attractions. Respond only in Korean."),
                        new GptMessage("user", gptPrompt)
                ))
                .build();

        GptResponse gptResponse = gptApiClient.callGptApi(gptRequest);

        String fullGptResponseContent = gptResponse.getChoices().getFirst().getMessage().getContent();
        
        List<String> placeNames = Arrays.stream(fullGptResponseContent.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .filter(name -> name.length() <= 20)
                .filter(name -> !name.contains("추천 없음"))
                .distinct()
                .toList();

        AtomicInteger order = new AtomicInteger(1);
        List<PlaceResponse> placeResponses = new ArrayList<>();

        placeNames.forEach(name -> {
            Place place = new Place();
            place.setTripId(trip.getId());
            place.setName(name);
            place.setOrderIndex(order.getAndIncrement());
            Place savedPlace = placeRepository.save(place);

            placeResponses.add(new PlaceResponse(savedPlace.getId(), savedPlace.getTripId(), savedPlace.getName(), savedPlace.getCategory(), savedPlace.getAddress(), savedPlace.getLatitude(), savedPlace.getLongitude(), savedPlace.getDescription(), savedPlace.getOrderIndex()));
        });

        return placeResponses;
    }
}
