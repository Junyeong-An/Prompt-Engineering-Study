package com.ChatGpt.api.dto;

import com.ChatGpt.domain.Message;
import java.util.ArrayList;
import java.util.List;

public record ChatGptRequestDTO(String model, List<Message> messages) {

    public ChatGptRequestDTO(String model, String prompt) {
        this(model, List.of(new Message("user", prompt)));
    }

    public ChatGptRequestDTO(String model, List<Message> previousMessages, String newPrompt) {
        this(model, appendNewMessage(previousMessages, newPrompt));
    }

    private static List<Message> appendNewMessage(List<Message> previousMessages, String newPrompt) {
        List<Message> updatedMessages = new ArrayList<>(previousMessages);
        updatedMessages.add(new Message("user", newPrompt));
        return updatedMessages;
    }
}
