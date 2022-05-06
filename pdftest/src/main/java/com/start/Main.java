package com.start;
import com.Parsers.RtfParser;
import com.Parsers.HtmlParser;
import com.Parsers.Parser;
import javax.swing.text.BadLocationException;
import java.io.IOException;



public class Main {
    public static String Detect_type(String file_name) {
//        TODO need to write type detector
        return "None";
    }


    public static void main(String[] args) throws BadLocationException, IOException {
        String type_of_file = "";
        for (String file_name : args) {
            type_of_file = Detect_type(file_name);
            if (type_of_file.equals("html")) {
                Parser technology = new HtmlParser();
                technology.Parse(file_name);
            } else if (type_of_file.equals("rtf")) {
                Parser technology = new RtfParser();
                technology.Parse(file_name);
            }
        }
    }
}
