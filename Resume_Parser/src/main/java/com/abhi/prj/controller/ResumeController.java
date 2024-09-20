package com.abhi.prj.controller;

import com.abhi.prj.service.ResumeParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeParserService resumeParserService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            // Convert MultipartFile to File
            File convFile = new File(file.getOriginalFilename());
            file.transferTo(convFile);

            // Parse and save resume
            String result = resumeParserService.parseAndSaveResume(convFile);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload and process file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
