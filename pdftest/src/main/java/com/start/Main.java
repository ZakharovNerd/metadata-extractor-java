package pdftest.src.main.java.com.start;
import pdftest.src.main.java.com.jumpstart.Extractor;
import javax.swing.text.BadLocationException;

public class Main {
    public static void main(String[] args) throws BadLocationException {
        for (String file_name : args) {
            Extractor extr = new Extractor();
            extr.extract(file_name);
        }
    }
}
