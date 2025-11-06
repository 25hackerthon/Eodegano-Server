package team.eodegano.global.gpt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GptResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<GptChoice> choices;
}
