package com.example.demo.entity;

import java.time.LocalDateTime;

public class SnapshotMetrics {
    private String filename;
    private LocalDateTime fetchedAt;
    private long wordCount;
    private int uniqueTermCount;
    private double avgSentenceLength;
    private String checksumSha256;

    public SnapshotMetrics() {}

    // getters and setters
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public LocalDateTime getFetchedAt() { return fetchedAt; }
    public void setFetchedAt(LocalDateTime fetchedAt) { this.fetchedAt = fetchedAt; }

    public long getWordCount() { return wordCount; }
    public void setWordCount(long wordCount) { this.wordCount = wordCount; }

    public int getUniqueTermCount() { return uniqueTermCount; }
    public void setUniqueTermCount(int uniqueTermCount) { this.uniqueTermCount = uniqueTermCount; }

    public double getAvgSentenceLength() { return avgSentenceLength; }
    public void setAvgSentenceLength(double avgSentenceLength) { this.avgSentenceLength = avgSentenceLength; }

    public String getChecksumSha256() { return checksumSha256; }
    public void setChecksumSha256(String checksumSha256) { this.checksumSha256 = checksumSha256; }
}
