package pdftest.src.main.java.com.jumpstart;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.IOException;
import java.io.*;


public class Extractor {

    public void extract(String file_name) throws BadLocationException {
        DefaultStyledDocument styledDoc = null;
        try {
            styledDoc = new DefaultStyledDocument();
            InputStream is = new FileInputStream(file_name);
            new RTFEditorKit().read(is, styledDoc, 0);

        } catch (BadLocationException e) {
            System.out.println("BadLocationException occurred when extracting text from RTF file");
        } catch (IOException e) {
            System.out.println("IOException occurred when extracting text from RTF file");
        }
        System.out.println(styledDoc.getText(0, styledDoc.getLength()));
    }
}