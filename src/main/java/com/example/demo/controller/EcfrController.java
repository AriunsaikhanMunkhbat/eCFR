package com.example.demo.controller;

import com.example.demo.service.EcfrService;
import com.example.demo.service.SnapshotMetrics;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EcfrController {

    private final EcfrService service;

    public EcfrController(EcfrService service) {
        this.service = service;
    }

    @PostMapping("/download")
    public String download() {
        try {
            service.download();
            return "Downloaded current snapshot (previous saved).";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/current")
    public SnapshotMetrics current() throws IOException {
        return service.currentMetrics();
    }

    @GetMapping("/previous")
    public SnapshotMetrics previous() throws IOException {
        return service.previousMetrics();
    }
}
