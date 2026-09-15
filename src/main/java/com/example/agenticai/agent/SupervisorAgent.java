package com.example.agenticai.agent;
import com.example.agenticai.model.*;
import com.example.agenticai.rag.RagService;
import com.example.agenticai.tool.*;
import com.example.agenticai.memory.ConversationMemory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.*;
import java.util.*;
@Service public class SupervisorAgent {
    final OllamaClient llm;
    final RagService rag;
    final McpToolRegistry reg;
    final ConversationMemory mem;
    final ObjectMapper om=new ObjectMapper();
    public SupervisorAgent(OllamaClient l,RagService r,McpToolRegistry g,ConversationMemory m) {
        llm=l;
        rag=r;
        reg=g;
        mem=m;
    }
    public ChatResponse run(String q,String sid) {
        var hits=rag.retrieve(q);
        String ctx=String.join("\\n",hits);
        var msgs=new ArrayList<Map<String,String>>();
        msgs.add(Map.of("role","system","content","You are an enterprise Supervisor Agent. Use knowledge for company questions and tools for customer/order/arithmetic. Return ONLY JSON: {type:answer,content:string} or {type:tool_call,tool:string,arguments:object}. Never invent tool results."));
        msgs.add(Map.of("role","user","content","Question: "+q+"\\nKnowledge:\\n"+(ctx.isBlank()?"NONE":ctx)));
        var trace=new ArrayList<Map<String,Object>>();
        for (int step=1;
        step<=6;
        step++) {
            try {
                Map d=om.readValue(llm.chat(msgs),Map.class);
                if ("answer".equals(d.get("type"))) {
                    String a=String.valueOf(d.get("content"));
                    mem.add(sid,"user",q);
                    mem.add(sid,"assistant",a);
                    return new ChatResponse(a,hits,trace,step);
                }
                String n=String.valueOf(d.get("tool"));
                Map<String,Object>a=(Map<String,Object>)d.getOrDefault("arguments",Map.of());
                McpTool t=reg.get(n);
                Object result=t==null?Map.of("error","Unknown tool"):t.execute(a);
                trace.add(Map.of("step",step,"tool",n,"arguments",a,"result",result));
                msgs.add(Map.of("role","assistant","content",om.writeValueAsString(d)));
                msgs.add(Map.of("role","user","content","Tool result: "+om.writeValueAsString(result)+". Continue."));
            }
            catch (Exception e) {
                return new ChatResponse("Agent error: "+e.getMessage(),hits,trace,step);
            }
        }
        return new ChatResponse("Agent step limit reached.",hits,trace,6);
    }
}
