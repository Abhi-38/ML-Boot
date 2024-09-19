package com.abhi.prj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abhi.prj.entity.Resume;

public class ResumeParser {

    public Resume parseResume(String extractedText) {
        Resume resume = new Resume();

        // Extract name (as the first line in the resume)
        String[] lines = extractedText.split("\\n");
        if (lines.length > 0) {
            resume.setName(lines[0].trim());
        }

        // Extract contact number
        String phoneNumber = extractPhoneNumber(extractedText);
        resume.setContact(phoneNumber);

        // Extract email
        String email = extractEmail(extractedText);
        resume.setEmail(email);

        // Extract education
        List<String> education = extractEducation(extractedText);
        resume.setEducation(education);

        // Extract skills
        List<String> skills = extractSkills(extractedText);
        resume.setSkills(skills);

        // Extract work experience
        List<String> experience = extractWorkExperience(extractedText);
        resume.setWorkExperience(experience);

        // Extract projects
        List<String> projects = extractProjects(extractedText);
        resume.setProjects(projects);

        return resume;
    }

    private String extractPhoneNumber(String text) {
        Pattern pattern = Pattern.compile("\\b(\\+\\d{1,3})?\\s?\\d{10}\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "Not Found";
    }

    private String extractEmail(String text) {
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "Not Found";
    }

    private List<String> extractEducation(String text) {
        List<String> education = new ArrayList<>();
        Pattern pattern = Pattern.compile("(B\\.Tech|HSC|SSC|Diploma|CGPA)\\s?.*\\d{4}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            education.add(matcher.group().trim());
        }
        return education;
    }

    private List<String> extractSkills(String text) {
        List<String> skills = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?i)Skills\\s*:\\s*(.*?)\\n");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String skillsText = matcher.group(1).trim();
            for (String skill : skillsText.split(",|;")) {
                skills.add(skill.trim());
            }
        }
        return skills;
    }

    private List<String> extractWorkExperience(String text) {
        List<String> experience = new ArrayList<>();
        Pattern pattern = Pattern.compile("(Intern|Engineer|Project|Trainee|Developer).*\\d{4}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            experience.add(matcher.group().trim());
        }
        return experience;
    }

    private List<String> extractProjects(String text) {
        List<String> projects = new ArrayList<>();
        Pattern pattern = Pattern.compile("(Project|Dashboard|Cartoonify).*\\d{4}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            projects.add(matcher.group().trim());
        }
        return projects;
    }
}
