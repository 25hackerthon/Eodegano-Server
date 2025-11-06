package team.eodegano.global.gpt.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GptRequest {
    private String model;
    private List<GptMessage> messages;
}
