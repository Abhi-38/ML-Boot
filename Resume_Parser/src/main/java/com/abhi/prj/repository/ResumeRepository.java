package com.abhi.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.prj.entity.Resume;


@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
