package com.carloso.ai.geminiapi.dto;

import java.util.List;

public record ModelListResponse(String object, List<GeminiModel> data) {
}
