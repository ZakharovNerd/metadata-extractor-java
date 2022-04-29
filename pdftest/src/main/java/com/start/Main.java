package com.start;
import com.jumpstart.Extractor;
import com.jumpstart.HtmlParser;
import javax.swing.text.BadLocationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        for (String file_name : args) {
            HtmlParser extr = new HtmlParser();
            System.out.println(file_name);
            extr.parse(file_name);
        }
    }
}
