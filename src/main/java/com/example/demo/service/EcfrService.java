package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.net.HttpURLConnection;


@Service
public class EcfrService {

    private final AnalysisService analysis;

    private final Path currentFile;
    private final Path previousFile;

    public EcfrService(AnalysisService analysis,
                       @Value("${ecfr.storage.dir}") String storageDir) throws IOException { //See ecfr.storage.dir value in application.properties.
        this.analysis = analysis;

        Path storagePath = Paths.get(storageDir);
        Files.createDirectories(storagePath);

        this.currentFile = storagePath.resolve("current.json");
        this.previousFile = storagePath.resolve("previous.json");
    }

    public void download() throws IOException {
        System.out.println(">>> download() start");

        if (Files.exists(currentFile)) {
            Files.copy(currentFile, previousFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">>> previous.json updated");
        }

        System.out.println(">>> before HTTP call");

        URL url = new URL("https://www.ecfr.gov/api/versioner/v1/versions/title-1.json"); // URL can be parameterized to add remaining Titles


        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);

        int status = conn.getResponseCode();

        InputStream stream =
                (status >= 200 && status < 300)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

        String json;
        try (stream) {
            json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }


        System.out.println(">>> after HTTP call, bytes=" + json.length());

        Files.writeString(currentFile, json, StandardCharsets.UTF_8);
        System.out.println(">>> current.json written");
    }

    public SnapshotMetrics metrics(Path file) throws IOException {
        String json = Files.readString(file, StandardCharsets.UTF_8);
        long words = analysis.wordCount(json);
        int unique = analysis.uniqueTerms(json);
        double avgSent = analysis.avgSentenceLength(json);
        String checksum = analysis.checksum(json);

        return new SnapshotMetrics(words, unique, avgSent, checksum);
    }

    public SnapshotMetrics currentMetrics() throws IOException {
        if (!Files.exists(currentFile)) return null;
        return metrics(currentFile);
    }

    public SnapshotMetrics previousMetrics() throws IOException {
        if (!Files.exists(previousFile)) return null;
        return metrics(previousFile);
    }
}
