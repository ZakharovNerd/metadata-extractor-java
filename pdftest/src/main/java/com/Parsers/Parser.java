package main.java.com.Parsers;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public interface Parser {
    void Parse(String file_name) throws BadLocationException, IOException;
}
