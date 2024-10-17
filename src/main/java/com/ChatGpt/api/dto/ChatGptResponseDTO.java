package com.ChatGpt.api.dto;

import com.ChatGpt.domain.Message;
import java.util.List;

public record ChatGptResponseDTO(List<Choice> choices) {

    public record Choice(int index, Message message) {
    }

}
