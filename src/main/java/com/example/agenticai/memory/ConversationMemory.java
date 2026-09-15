package com.example.agenticai.memory;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class ConversationMemory {
    private final Map<String,List<Map<String,String>>> m=new HashMap<>();
    public List<Map<String,String>> get(String id) {
        return m.getOrDefault(id,List.of());
    }
    public void add(String id,String r,String c) {
        var x=new ArrayList<>(get(id));
        x.add(Map.of("role",r,"content",c));
        m.put(id,x.size()>10?new ArrayList<>(x.subList(x.size()-10,x.size())):x);
    }
    public void clear(String id) {
        m.remove(id);
    }
}
