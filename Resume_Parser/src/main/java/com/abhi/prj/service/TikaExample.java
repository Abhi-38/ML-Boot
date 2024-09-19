package com.abhi.prj.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

public class TikaExample {
   
    public static void main(String[] args) throws TikaException {
        Tika tika = new Tika();
        try {
            String text = tika.parseToString(new File("C:\\Users\\HP\\Downloads\\Abhishek-Chougule-9765759238.pdf"));
            System.out.println("here is data: "+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

