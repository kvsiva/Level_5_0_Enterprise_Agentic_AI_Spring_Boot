package com.example.agenticai.tool;
import java.util.Map;
public interface McpTool {
    String name();
    String description();
    Object execute(Map<String,Object> args);
}
