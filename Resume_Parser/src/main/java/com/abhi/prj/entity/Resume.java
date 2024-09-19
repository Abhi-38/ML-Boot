package com.abhi.prj.entity;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private String name;
    private String email;
    private String contact;
    private List<String> education;
    private List<String> skills;
    private List<String> projects;
    private List<String> workExperience;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public List<String> getEducation() {
		return education;
	}
	public void setEducation(List<String> education) {
		this.education = education;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getProjects() {
		return projects;
	}
	public void setProjects(List<String> projects) {
		this.projects = projects;
	}
	public List<String> getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(List<String> workExperience) {
		this.workExperience = workExperience;
	}
	/*
	 * public Resume(String name, String email, String contact, List<String>
	 * education, List<String> skills, List<String> projects, List<String>
	 * workExperience) { super(); this.name = name; this.email = email; this.contact
	 * = contact; this.education = education; this.skills = skills; this.projects =
	 * projects; this.workExperience = workExperience; }
	 */

}