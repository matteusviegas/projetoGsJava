// package br.com.globalsolution.agrosat.ai.infrastructure.springAi;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.io.Resource;
// import org.springframework.core.io.support.ResourcePatternResolver;
// import org.springframework.stereotype.Component;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class RagDocumentLoader implements CommandLineRunner {

// private final ResourcePatternResolver resourcePatternResolver;

// private final PdfIngestService pdfIngestService;

// @Override
// public void run(String... args) throws Exception {
// Resource[] resources =
// resourcePatternResolver.getResources("classpath:/rag/*.pdf");

// for (Resource resource : resources) {
// log.info("Carregando documento RAG: {}", resource.getFilename());
// pdfIngestService.ingest(resource);
// }
// }

// }
