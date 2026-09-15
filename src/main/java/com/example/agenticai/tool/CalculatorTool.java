package com.example.agenticai.tool;
import org.springframework.stereotype.Component;
import java.util.*;
@Component public class CalculatorTool implements McpTool {
    public String name() {
        return "calculator";
    }
    public String description() {
        return "Calculate basic arithmetic";
    }
    public Object execute(Map<String,Object>a) {
        String e=String.valueOf(a.getOrDefault("expression",""));
        if (!e.matches("[0-9+*/(). %\\-]+"))return Map.of("error","Invalid arithmetic expression");
        return Map.of("expression",e,"note","Educational demo; use a safe expression library in production");
    }
}
