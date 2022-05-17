package com.start;
import com.Parsers.RtfParser;
import com.Parsers.HtmlParser;
import com.Parsers.Parser;
import pdftest.src.main.java.com.fileTypeAnalyzer.detectType;
import javax.swing.text.BadLocationException;
import java.io.IOException;



public class Main {
    public static String detectType(String file_name) throws IOException {
//        TODO need to write type detector
        String fileType = null;
        detectType detect = new detectType();
        if (detect.detector(file_name, "{\\rtf")) fileType = "rtf";
        else if (detect.detector(file_name, "<!doctype>")) fileType = "html";

        return fileType;
    }


    public static void main(String[] args) throws BadLocationException, IOException {
        String type_of_file = "";
        for (String file_name : args) {
            type_of_file = detectType(file_name);
            System.out.println(type_of_file);
            if (type_of_file.equals("html")) {
                Parser technology = new HtmlParser();
                technology.Parse(file_name);
            } else if (type_of_file.equals("rtf")) {
                Parser technology = new RtfParser();
                technology.Parse(file_name);
            } else if (type_of_file.equals(null)) {
                System.out.println("input is not html or rtf file");
            }
        }
    }
}
