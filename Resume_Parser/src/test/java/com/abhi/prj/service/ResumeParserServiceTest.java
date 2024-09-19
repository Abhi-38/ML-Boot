package com.abhi.prj.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;  // Ensure this import is present

@SpringBootTest
public class ResumeParserServiceTest {

    @Autowired
    private ResumeParserService resumeParserService;

    @Test
    public void testParseResume() {
        // Provide the path to the PDF or image file to test
        File file = new File("C:/Users/HP/Downloads/Abhishek-Chougule-9765759238.pdf");

        // Test the parsing function
        String parsedText = resumeParserService.parseResume(file);

        // Assert that the parsing was successful and returned some text
        assertNotNull(parsedText, "Parsed text should not be null");
        System.out.println("Parsed Text: " + parsedText);
    }
}

