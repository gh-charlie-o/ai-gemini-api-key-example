package com.carloso.ai.geminiapi.controller;

import com.carloso.ai.geminiapi.dto.GeminiModel;
import com.carloso.ai.geminiapi.dto.PromptRequest;
import com.carloso.ai.geminiapi.service.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Gemini API", description = "Operations for interacting with Google's Gemini AI models")
public class GeminiController {
    private final Logger LOGGER = LoggerFactory.getLogger(GeminiController.class);

    private final GeminiService geminiService;

    @Autowired
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @Operation(
            summary = "Submit a prompt to Gemini AI",
            description = "Sends a text prompt to Gemini and returns the AI-generated response",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "500", description = "Error processing request")
            }
    )
    @PostMapping("/query")
    public String queryPost(@RequestBody PromptRequest request) {
        return this.geminiService.query(request.prompt());
    }

    @Operation(
            summary = "Get available Gemini models",
            description = "Returns a list of available Gemini AI models",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeminiModel.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Error retrieving models")
            }
    )
    @GetMapping("/models")
    public List<GeminiModel> models() {
        return this.geminiService.getModels();
    }
}
