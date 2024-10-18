package com.ChatGpt.application;

import com.ChatGpt.api.dto.AnswerResponseDTO;
import com.ChatGpt.api.dto.ChatGptRequestDTO;
import com.ChatGpt.api.dto.ChatGptResponseDTO;
import com.ChatGpt.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    private Map<String, List<Message>> conversationHistory = new HashMap<>();

    public AnswerResponseDTO chat(String prompt, String userId) {
        List<Message> previousMessages = conversationHistory.getOrDefault(userId, new ArrayList<>());

        ChatGptRequestDTO request = new ChatGptRequestDTO(model, previousMessages, prompt);
        ChatGptResponseDTO chatGptResponseDTO = template.postForObject(apiURL, request, ChatGptResponseDTO.class);

        previousMessages.add(chatGptResponseDTO.choices().get(0).message());

        conversationHistory.put(userId, List.copyOf(previousMessages));

        return new AnswerResponseDTO(chatGptResponseDTO.choices().get(0).message().getContent());
    }
}
