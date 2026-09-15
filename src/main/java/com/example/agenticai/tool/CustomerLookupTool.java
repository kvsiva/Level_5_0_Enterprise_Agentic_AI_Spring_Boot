package com.example.agenticai.tool;
import org.springframework.stereotype.Component;
import java.util.*;
@Component public class CustomerLookupTool implements McpTool {
    public String name() {
        return "customer_lookup";
    }
    public String description() {
        return "Look up customer status by customer_id";
    }
    public Object execute(Map<String,Object>a) {
        return switch (String.valueOf(a.get("customer_id"))) {
            case "C1001"->Map.of("id","C1001","name","Anita","status","ACTIVE");
            case "C1002"->Map.of("id","C1002","name","Ravi","status","ACTIVE");
            case "C1003"->Map.of("id","C1003","name","Priya","status","BLOCKED");
            default->Map.of("error","Customer not found");
        }
        ;
    }
}
