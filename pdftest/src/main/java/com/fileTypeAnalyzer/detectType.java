<<<<<<< HEAD
package com.fileTypeAnalyzer;
=======
package pdftest.src.main.java.com.fileTypeAnalyzer;
>>>>>>> 004f9e2b44cfd27211a146152501b5fc657a48d3
import java.io.*;
import java.util.Arrays;

public class detectType {

    public static int[] computePrefix(byte[] input) {
        int[] pi = new int[input.length];

        int k = 0;
        for(int q = 2; q < input.length; q++) {
            while (k > 0 && input[k] != input[q]) {
                k = pi[k];
            }
            if (input[k] == input[q]) {
                k = k + 1;
            }
            pi[q] = k;
        }
        return pi;
    }

    public static boolean findKMP(byte [] buffer, byte [] key) {
        int[] prefix = computePrefix(key);
        int range = 0;
        int len = key.length;
        boolean result = false;
        int start = 0;
        int end = key.length;
        while (end <= buffer.length) {
            byte[] subarray =  Arrays.copyOfRange(buffer, start, end);
            if (Arrays.equals(subarray, key)) {
                result = true;
                break;
            } else {
                for (int k = len-1; k >= 0; k--) {
                    if (Arrays.equals(Arrays.copyOfRange(subarray, 0, k), Arrays.copyOfRange(key, 0, k))) {
                        range = k;
                        break;
                    }
                    start = start + range - prefix[k];
                    end = end + range - prefix[k];
                    range = 0;
                }
                return result;
            }




        }
        return result;
    }


    public static boolean detector(String stringName, String fileTypePattern) throws IOException {

        File file = new File(stringName);
        byte[] inputArg = fileTypePattern.getBytes();
        byte[] bytes = new byte[(int) file.length()];

        try(FileInputStream fis = new FileInputStream(file)){
            fis.read(bytes);
            if (findKMP(bytes, inputArg)) {
                return true;
            } else {
                return false;
            }

        }
    }
}