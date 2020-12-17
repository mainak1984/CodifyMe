package edu.codifyme.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseIP {
    private static String PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    public static void main (String[] args) {
        Pattern pattern = Pattern.compile(PATTERN);

        try {
            FileInputStream fis = new FileInputStream("E:\\workspace\\GitHub\\CodifyMe\\src\\main\\java\\edu\\codifyme" +
                    "\\practice\\ip.txt");
            Scanner sc = new Scanner(fis);

                    while (sc.hasNextLine()) {
                        String fullLine = sc.nextLine();
                        String[] lines = fullLine.split(" ");

                        for (String line: lines) {
                            Matcher match = pattern.matcher(line);

                    while (match.find()) {
                        String find = match.group();
                        if (find.equals(line)) {
                            System.out.println(find);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
