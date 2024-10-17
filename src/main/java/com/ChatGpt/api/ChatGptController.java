package com.ChatGpt.api;

import com.ChatGpt.api.dto.AnswerResponseDTO;

import com.ChatGpt.application.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGptService chatGptService;

    @GetMapping("/chat")
    public ResponseEntity<AnswerResponseDTO> chat(@RequestParam(name = "prompt") String prompt,
                                                  @RequestParam(name = "userId") String userId) {

        AnswerResponseDTO answerResponseDTO = chatGptService.chat(prompt, userId);

        return ResponseEntity.ok(answerResponseDTO);
    }
}
