package main.java.com.Parsers;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import main.java.com.Parsers.Parser;
import org.jsoup.nodes.Element;

import java.io.*;


public class RtfParser implements Parser {
    @Override
    public void Parse(String file_name) throws BadLocationException {
        DefaultStyledDocument styledDoc = null;
        try {
            styledDoc = new DefaultStyledDocument();
            InputStream is = new FileInputStream(file_name);
            new RTFEditorKit().read(is, styledDoc, 0);
            FileWriter writer = new FileWriter("result.txt", true);
            writer.write("\n------------------------------- " + file_name + " -------------------------------\n");
            writer.write(styledDoc.getText(0, styledDoc.getLength()));
            writer.write("\n--------------------------------------------------------------\n");
            writer.close();

        } catch (BadLocationException e) {
            System.out.println("BadLocationException occurred when extracting text from RTF file:" + file_name);
        } catch (IOException e) {
            System.out.println("IOException occurred when extracting text from RTF file: " + file_name);
        }
    }
}