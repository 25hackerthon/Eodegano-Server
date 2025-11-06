package team.eodegano.global.gpt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GptChoice {
    private int index;
    private GptMessage message;
    private String finish_reason;
}
