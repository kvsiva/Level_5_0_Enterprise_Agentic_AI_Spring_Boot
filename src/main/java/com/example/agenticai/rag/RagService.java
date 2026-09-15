package com.example.agenticai.rag;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class RagService {
    private final List<String> k=List.of("New backend services should use Java 21 unless compatibility requires otherwise.","New Java backend services should use Spring Boot 3.x.","Services should be independently deployable.","Kafka can be used for asynchronous event-driven integration.","Docker packages services and Kubernetes/OpenShift can orchestrate containers.","Production APIs require authentication and authorization.","Secrets must not be hard-coded.");
    public List<String> retrieve(String q) {
        String x=q.toLowerCase();
        return k.stream().filter(s->Arrays.stream(x.split("\\W+")).anyMatch(w->w.length()>3&&s.toLowerCase().contains(w))).limit(4).toList();
    }
}
