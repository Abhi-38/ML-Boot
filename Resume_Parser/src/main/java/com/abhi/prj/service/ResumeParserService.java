package com.abhi.prj.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abhi.prj.entity.Resume;
import com.abhi.prj.repository.ResumeRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeParserService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Value("${tesseract.datapath}")
    private String tessDataPath;

    public String parseAndSaveResume(File file) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPath); // Set the tessdata path from properties

        try {
            // Parse resume text from file
            String parsedText = tesseract.doOCR(file);

            // Map parsed text to Resume fields (this part needs manual extraction logic for each field)
            Resume resume = new Resume();
            resume.setName(extractName(parsedText));  // Example field
            resume.setEmail(extractEmail(parsedText));
            resume.setContact(extractPhoneNumber(parsedText));
            resume.setEducation(extractEducation(parsedText));
            resume.setSkills(extractSkills(parsedText));
            resume.setWorkExperience(extractExperience(parsedText));
            resume.setProjects(extractProjects(parsedText));
            // Add the rest of your field extraction logic

            // Save to database
            resumeRepository.save(resume);

            return "Resume parsed and saved successfully!";
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Failed to parse the resume.";
        }
    }

    private String extractName(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && Character.isUpperCase(line.charAt(0))) {
                // Assume the first non-empty line starting with an uppercase letter is the name
                return line;
            }
        }
        return "Name Not Found";
    }
    
    private String extractPhoneNumber(String text) {
        Pattern pattern = Pattern.compile("Contact no\\.:\\s*(\\d{10})");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : "Not Found";
    }

    private String extractEmail(String text) {
        Pattern pattern = Pattern.compile("([\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,4})");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : "Not Found";
    }

    private List<String> extractEducation(String text) {
        List<String> education = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?s)EDUCATION(.*?)TECHNICAL SKILLS");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String educationText = matcher.group(1);
            Pattern degreePattern = Pattern.compile("(B\\.Tech|HSC|SSC).*?(?=\\n|$)");
            Matcher degreeMatcher = degreePattern.matcher(educationText);
            while (degreeMatcher.find()) {
                education.add(degreeMatcher.group().trim());
            }
        }
        return education;
    }

    private List<String> extractSkills(String text) {
        List<String> skillsList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?s)TECHNICAL SKILLS(.*?)INTERNSHIPS");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String skillsText = matcher.group(1);
            Pattern skillPattern = Pattern.compile("(\\w+):(.+)");
            Matcher skillMatcher = skillPattern.matcher(skillsText);
            while (skillMatcher.find()) {
                skillsList.add(skillMatcher.group().trim());
            }
        }
        return skillsList;
    }

    private List<String> extractExperience(String text) {
        List<String> experienceList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?s)INTERNSHIPS(.*?)PROJECTS");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String experienceText = matcher.group(1);
            Pattern internshipPattern = Pattern.compile("(?s)(\\w+.*?Intern.*?)(?=\\n\\n|$)");
            Matcher internshipMatcher = internshipPattern.matcher(experienceText);
            while (internshipMatcher.find()) {
                experienceList.add(internshipMatcher.group(1).replaceAll("\n", " ").trim());
            }
        }
        return experienceList;
    }

    private List<String> extractProjects(String text) {
        List<String> projectsList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?s)PROJECTS(.*?)CERTIFICATIONS");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String projectsText = matcher.group(1);
            Pattern projectPattern = Pattern.compile("(?s)(\\w+.*?)(?=\\n\\n|$)");
            Matcher projectMatcher = projectPattern.matcher(projectsText);
            while (projectMatcher.find()) {
                projectsList.add(projectMatcher.group(1).replaceAll("\n", " ").trim());
            }
        }
        return projectsList;
    }
}