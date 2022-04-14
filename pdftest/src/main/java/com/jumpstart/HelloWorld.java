package com.jumpstart;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.IOException;
import java.io.*;


public class HelloWorld {

    public static String Main(String[] args) throws BadLocationException {
        DefaultStyledDocument styledDoc = null;
        try {
            styledDoc = new DefaultStyledDocument();
            InputStream is = new FileInputStream(args[0]);
            new RTFEditorKit().read(is, styledDoc, 0);

        } catch (BadLocationException e) {
            System.out.println("BadLocationException occurred when extracting text from RTF file");
        } catch (IOException e) {
            System.out.println("IOException occurred when extracting text from RTF file");
        }
        return styledDoc.getText(0, styledDoc.getLength());
    }
}