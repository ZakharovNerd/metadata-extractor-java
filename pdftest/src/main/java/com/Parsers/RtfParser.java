package com.Parsers;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import com.Parsers.Parser;
import java.io.*;


public class RtfParser implements Parser {
    @Override
    public void Parse(String file_name) throws BadLocationException {
        DefaultStyledDocument styledDoc = null;
        try {
            styledDoc = new DefaultStyledDocument();
            InputStream is = new FileInputStream(file_name);
            new RTFEditorKit().read(is, styledDoc, 0);

        } catch (BadLocationException e) {
            System.out.println("BadLocationException occurred when extracting text from RTF file:" + file_name);
        } catch (IOException e) {
            System.out.println("IOException occurred when extracting text from RTF file: " + file_name);
        }
    }
}