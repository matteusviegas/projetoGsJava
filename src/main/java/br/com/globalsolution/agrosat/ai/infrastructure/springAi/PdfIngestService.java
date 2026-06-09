// package br.com.globalsolution.agrosat.ai.infrastructure.springAi;

// import java.io.IOException;
// import java.util.List;
// import java.util.Map;

// import org.springframework.ai.document.Document;
// import org.springframework.ai.reader.ExtractedTextFormatter;
// import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
// import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
// import org.springframework.ai.transformer.splitter.TokenTextSplitter;
// import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.core.io.ByteArrayResource;
// import org.springframework.core.io.Resource;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class PdfIngestService {

// private final VectorStore vectorStore;

// public int ingest(Resource resource) throws IOException {
// String fileName = resource.getFilename();

// log.info("INICIANDO INGESTAO DO ARQUIVO: {}", fileName);

// PdfDocumentReaderConfig readerConfig = PdfDocumentReaderConfig.builder()
// .withPageExtractedTextFormatter(ExtractedTextFormatter.defaults())
// .withPagesPerDocument(1)
// .build();

// PagePdfDocumentReader reader = new PagePdfDocumentReader(resource,
// readerConfig);

// List<Document> pages = reader.get();

// pages.forEach(doc -> doc.getMetadata().putAll(
// Map.of("source", fileName)));

// TokenTextSplitter splitter = new TokenTextSplitter(
// 512,
// 64,
// 5,
// 10000,
// true);

// List<Document> chunks = splitter.apply(pages);

// vectorStore.add(chunks);

// log.info("PDF {} indexado com {} chunks", fileName, chunks.size());

// return chunks.size();
// }

// public int ingest(MultipartFile file) throws IOException {
// Resource resource = new ByteArrayResource(file.getBytes()) {
// @Override
// public String getFilename() {
// return file.getOriginalFilename();
// }
// };

// return ingest(resource);
// }

// }
