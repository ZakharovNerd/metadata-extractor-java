package com.start;
import com.Parsers.RtfParser;
import com.Parsers.HtmlParser;
import com.Parsers.Parser;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fileTypeAnalyzer.detectType;



public class Main {
    public static String Detect_type(String file_name) {
//        TODO need to write type detector
//        String fileType = null;
//        detectType detect = new detectType();
//        try {
//            if (detect.detector(file_name, "{\\rtf"))
//                fileType = "rtf";
//            else if (detect.detector(file_name, "<!doctype>"))
//                fileType = "html";
//        } catch (IOException e) {
//            System.out.println("IOException occurred when extracting text from HTML file: " + file_name);
//        }
//        return fileType;
        if (file_name.substring(file_name.indexOf("."), file_name.length()).equals(".html")) {
            return "html";
        } else if  (file_name.substring(file_name.indexOf("."), file_name.length()).equals(".rtf")) {
            return "rtf";
        }
        return "";
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
        System.out.println(root.getAbsolutePath());
        File pared_dir = new File(root.getAbsolutePath() + "/Parsed");
        if (!pared_dir.exists()) {
            pared_dir.mkdir();
        }
        searchFiles(root, fileList);
        if (fileList.size() == 0) {
            return;
        }
        String type_of_file = "";
        boolean parsed = false;
        for (File file_name : fileList) {
            System.out.println(file_name.getName());
            type_of_file = Detect_type(file_name.getName());//should be file_name.getAbsolutePath()!!!!!!!
            System.out.println(type_of_file);
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
                System.out.println("in cond to moove file in parsed dir");
                parsed = false;
                file_name.renameTo(new File(pared_dir, file_name.getName()));
            }
        }
    }
}
