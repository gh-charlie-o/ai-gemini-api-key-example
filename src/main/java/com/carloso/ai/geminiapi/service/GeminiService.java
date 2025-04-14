package com.carloso.ai.geminiapi.service;

import com.carloso.ai.geminiapi.dto.GeminiModel;
import com.carloso.ai.geminiapi.dto.ModelListResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GeminiService {
    private final ChatClient chatClient;
    private final RestClient restClient;

    @Value("${spring.ai.openai.api-key}")
    private String GEMINI_API_KEY;

    public GeminiService(ChatClient.Builder chatClientBuilder, RestClient.Builder restClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptions
                        .builder()
                        .build())
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();

        this.restClient = restClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

    public String query(String prompt) {
        return this.chatClient.prompt(prompt)
                .call()
                .content();
    }

    public List<GeminiModel> getModels() {
        ResponseEntity<ModelListResponse> response = restClient.get()
                .uri("/v1beta/openai/models")
                .header("Authorization","Bearer " + GEMINI_API_KEY)
                .retrieve()
                .toEntity(ModelListResponse.class);

        return response.getBody().data();
    }
}
