package com.start;
import com.Parsers.RtfParser;
import com.Parsers.HtmlParser;
import com.Parsers.Parser;
import javax.swing.text.BadLocationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.fileTypeAnalyzer.detectType;



public class Main {

    public static String Detect_type(String file_name) throws IOException {
//        TODO need to write type detector
        String fileType = "";
        detectType detect = new detectType();
        if (detect.detector(file_name, "{\\rtf")) fileType = "rtf";
        else if (detect.detector(file_name, "<!DOCTYPE")) fileType = "html";

        return fileType;
    }


    public static void searchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file: directoryFiles) {
                    if (file.isFile()) {
                        fileList.add(file);
                    }
                }
            }
        }

    }


    public static void main(String[] args) throws BadLocationException, IOException {
        ArrayList<File> fileList = new ArrayList<>();
        File root = new File("Files");
        File pared_dir = new File(root.getAbsolutePath() + "/Parsed");
        if (!pared_dir.exists()) {
            pared_dir.mkdir();
        }
        searchFiles(root, fileList);
        if (fileList.size() == 0) {
            System.out.println("You have no files to parse");
            return;
        }
        String type_of_file = "";
        boolean parsed = false;

        for (File file_name : fileList) {
            type_of_file = Detect_type(file_name.getAbsolutePath());//should be file_name.getAbsolutePath()!!!!!!!
            if (type_of_file.equals("html")) {
                Parser technology = new HtmlParser();
                technology.Parse(file_name.getAbsolutePath());
                parsed = true;
            } else if (type_of_file.equals("rtf")) {
                Parser technology = new RtfParser();
                technology.Parse(file_name.getAbsolutePath());
                parsed = true;
            }
            if (parsed) {
                parsed = false;
                file_name.renameTo(new File(pared_dir, file_name.getName()));
            }
        }
    }
}
