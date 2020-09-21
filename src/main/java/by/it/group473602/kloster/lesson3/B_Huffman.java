package by.it.group473602.kloster.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;


public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        return getDecodedString(getEncodedString(file), getCodes(file)); // 01001100100111
    }

    private Map<String, Character> getCodes (File file) throws FileNotFoundException {
        Map<String, Character> codes = new TreeMap<>();
        Scanner scanner = new Scanner(file);
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (Pattern.matches("[a-zA-Z]{1}: [0-1]{1,}", line))
                codes.put(line.split(": ")[1], line.split(": ")[0].charAt(0));
        }
        scanner.close();
        return codes;
    }


    private String getEncodedString(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line = new String();
        while(scanner.hasNext()) {
            line=scanner.nextLine();
            if(Pattern.matches("[0-1]{1,}", line))
                break;
        }
        scanner.close();
        return line;
    }

    private String getDecodedString(String encodedString, Map<String, Character> charecters) {
        StringBuilder currentCode = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for(int i=0;i<14;i++) {
            currentCode.append(encodedString.charAt(i));
            if(encodedString.charAt(i)=='0') {
                result.append(charecters.get(currentCode.toString()));
                currentCode = new StringBuilder();
            }
        }
        result.append(charecters.get(currentCode.toString()));
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group473602/kloster/lesson3/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}