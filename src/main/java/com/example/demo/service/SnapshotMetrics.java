package com.example.demo.service;

public class SnapshotMetrics {
    private long wordCount;
    private int uniqueTerms;
    private double avgSentenceLength;
    private String checksum;

    public SnapshotMetrics(long wordCount, int uniqueTerms, double avgSentenceLength, String checksum) {
        this.wordCount = wordCount;
        this.uniqueTerms = uniqueTerms;
        this.avgSentenceLength = avgSentenceLength;
        this.checksum = checksum;
    }

    public long getWordCount() { return wordCount; }
    public int getUniqueTerms() { return uniqueTerms; }
    public double getAvgSentenceLength() { return avgSentenceLength; }
    public String getChecksum() { return checksum; }
}
