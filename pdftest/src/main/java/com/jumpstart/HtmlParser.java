package com.jumpstart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.IOException;

public class HtmlParser {
    public void parse(String file_name) throws IOException {
        File input = new File(file_name);
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements list_of_paragraphs = doc.body().select("*");
        for (Element line : list_of_paragraphs) {
            if (!line.ownText().isEmpty()) {
                System.out.println(line.ownText());
            }
        }
    }
}
