# Level 5.0 Enterprise Agentic AI — Spring Boot

Java 21 + Spring Boot + Ollama/Qwen3 + RAG + MCP-style tools + Supervisor Agent + memory + guardrails + Actuator.

## Run
`ollama pull qwen3:8b`
`mvn spring-boot:run`
Open http://localhost:8080

## Test
- What Java version should new backend services use?
- What is the status of customer C1003?
- What is the status of order O1001?
- Calculate 1250 * 4 + 300.
- What is the status of customer C1001 and order O1003?

## Architecture
User -> Spring Boot API -> Guardrails -> Supervisor Agent -> RAG / MCP Tool Registry / Memory -> Qwen3 -> final answer.

The MCP layer is intentionally an educational local abstraction. Production should use a real MCP client/server, strict schemas, authorization, persistent memory, audit logs, observability, retries/timeouts, PII controls and human approval for high-impact actions.
