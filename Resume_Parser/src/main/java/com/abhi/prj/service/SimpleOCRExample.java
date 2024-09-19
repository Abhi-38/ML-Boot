package com.abhi.prj.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class SimpleOCRExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Path to the image file
        File imageFile = new File("C:\\Users\\HP\\Downloads\\Abhishek-Chougule-9765759238.pdf");

        // Path to the tessdata directory
        String tessDataPath = "C:\\Users\\HP\\OneDrive\\Desktop\\demo.txt";

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPath); // Set path to tessdata directory

        try {
            String text = tesseract.doOCR(imageFile);
            System.out.println("Extracted text: " + text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
