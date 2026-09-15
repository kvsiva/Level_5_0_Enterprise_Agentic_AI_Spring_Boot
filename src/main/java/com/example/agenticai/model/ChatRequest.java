package com.example.agenticai.model; import jakarta.validation.constraints.NotBlank; public record ChatRequest(@NotBlank String question,String sessionId){}
