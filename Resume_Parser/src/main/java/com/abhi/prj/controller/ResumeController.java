package com.abhi.prj.controller;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abhi.prj.service.ResumeParsingService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

	@Autowired
    private ResumeParsingService resumeParsingService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) throws TikaException {
        try {
            String content = resumeParsingService.parseResume(file);
            // Optionally process the extracted content further
            return ResponseEntity.ok("Resume parsed successfully! Content: " + content);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error parsing resume: " + e.getMessage());
        }
    }
}
