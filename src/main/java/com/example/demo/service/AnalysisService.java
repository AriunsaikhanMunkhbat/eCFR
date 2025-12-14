package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnalysisService {

    public long wordCount(String text) {
        if (text == null || text.isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public int uniqueTerms(String text) {
        if (text == null || text.isEmpty()) return 0;
        String[] words = text.toLowerCase().split("\\W+");
        Set<String> set = new HashSet<>();
        for (String w : words) if (!w.isEmpty()) set.add(w);
        return set.size();
    }

    public double avgSentenceLength(String text) {
        if (text == null || text.isEmpty()) return 0.0;
        String[] sentences = text.split("[.!?]+");
        long words = wordCount(text);
        return sentences.length == 0 ? 0 : ((double) words / sentences.length);
    }

    public String checksum(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
