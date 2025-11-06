package team.eodegano.global.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.eodegano.global.gpt.dto.GptRequest;
import team.eodegano.global.gpt.dto.GptResponse;

@Service
public class GptApiClient {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public GptApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GptResponse callGptApi(GptRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<GptRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(OPENAI_API_URL, entity, GptResponse.class);
    }
}
