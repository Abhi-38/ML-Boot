package com.abhi.prj.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ResumeParserService {

    @Value("${tesseract.datapath}")
    private String tessDataPath;

    public String parseResume(File file) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPath); // Set the tessdata path from properties
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}