// package br.com.globalsolution.agrosat.ai.service;

// import org.springframework.ai.chat.client.ChatClient;
// import
// org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
// import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class RagQueryService {

// private final ChatClient.Builder chatClientBuilder;

// private final VectorStore vectorStore;

// private static final String SYSTEM_PROMPT = """
// Você é um engenheiro agrônomo especializado em agricultura brasileira.

// Responda exclusivamente em JSON válido, sem markdown.

// Formato obrigatório:

// {
// "recommendation": "texto da recomendação",
// "priority": 1
// }

// Regras da prioridade:
// 1 = BAIXA
// 2 = MÉDIA
// 3 = ALTA

// Use:
// - 1 para ação preventiva ou baixo impacto
// - 2 para risco moderado ou recomendação importante
// - 3 para risco elevado ou ação urgente

// Baseie-se apenas nos documentos fornecidos.

// Contexto recuperado dos documentos:
// {question_answer_context}
// """;

// public String ask(String question) {

// ChatClient chatClient = chatClientBuilder
// .defaultSystem(SYSTEM_PROMPT)
// .build();

// String response = chatClient.prompt()
// .user(question)
// .advisors(new QuestionAnswerAdvisor(vectorStore))
// .call()
// .content();

// return response;
// }

// }
