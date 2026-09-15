package com.example.agenticai.guardrail;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class GuardrailService {
    private final List<String>b=List.of("ignore previous instructions","reveal system prompt","show password","show secret");
    public String validate(String q) {
        String x=q.toLowerCase();
        return b.stream().filter(x::contains).findFirst().map(s->"Blocked by safety guardrail: "+s).orElse(null);
    }
}
