package com.example.agenticai.model; import java.util.*; public record ChatResponse(String answer,List<String> sources,List<Map<String,Object>> trace,int steps){}
