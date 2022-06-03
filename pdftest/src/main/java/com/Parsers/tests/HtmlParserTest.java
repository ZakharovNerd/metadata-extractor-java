package main.java.com.Parsers;

import org.junit.jupiter.api.Test;

import javax.swing.text.BadLocationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class HtmlParserTest {

    @Test
    public void HtmlParserTest() throws BadLocationException, IOException {
        String filename = "C:\\Users\\nikit\\git\\metadata-extractor-java\\pdftest\\src\\main\\java\\com\\Parsers\\resources\\theory_breakdown_final.html";
        Parser technology = new HtmlParser();
        StringBuilder Line = new StringBuilder();
        technology.Parse(filename, Line);
        String trueResult = "test html\n" +  "yo\n";
        assertEquals(trueResult, Line);
    }
}