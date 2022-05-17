package com.Parsers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.text.BadLocationException;
import com.Parsers.Parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlParser implements Parser {
    @Override
    public void Parse(String file_name) throws BadLocationException {
        try {
            File input = new File(file_name);
            Document doc = Jsoup.parse(input, "UTF-8");
            Elements list_of_paragraphs = doc.body().select("*");
            FileWriter writer = new FileWriter("result.txt", true);
            writer.write("\n------------------------------- " + file_name + " -------------------------------\n");
            for (Element line : list_of_paragraphs) {
                if (!line.ownText().isEmpty()) {
                    writer.write(line.ownText());
                }
            }
            writer.write("\n--------------------------------------------------------------\n");
            writer.close();
        } catch (IOException e)  {
            System.out.println("IOException occurred when extracting text from HTML file: " + file_name);
        }
    }
}
