package edu.codifyme.practice;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReadWriteBigFile {
    static List<String> lines = new LinkedList<>();

    public static void main(String[] args) {
        File output = new File("E:\\workspace\\GitHub\\CodifyMe\\src\\main\\java\\edu\\codifyme\\practice\\output.txt");
        if (output.exists() && !output.delete()) {
            System.out.println("Error in deleting");
            return;
        }

        try {
//            readLargeFile();
            readLargeFileAlternate();
            writeLargeFile();
//            createLargeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readLargeFile() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\workspace\\GitHub\\CodifyMe\\src\\main\\java\\edu\\codifyme" +
                "\\practice\\input.txt");
        Scanner sc = new Scanner(fis, "UTF-8").useDelimiter(",");

        while(sc.hasNext()) {
            lines.add(sc.next());
        }

        if (sc.ioException() != null) {
            throw(sc.ioException());
        }

        fis.close();
        sc.close();
    }

    static void readLargeFileAlternate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\workspace\\GitHub" +
                "\\CodifyMe\\src\\main\\java\\edu\\codifyme\\practice\\input.txt")));

        String line;
        while( (line = br.readLine()) != null) {
            lines.add(line);
//            System.out.println(line);
        }

        br.close();
    }

    static void writeLargeFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\workspace\\GitHub" +
                "\\CodifyMe\\src\\main\\java\\edu\\codifyme\\practice\\output.txt")));

        for (String line: lines) {
            bw.write(line);
            bw.newLine();
        }

        bw.close();
    }

    static void createLargeFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\workspace\\GitHub" +
                "\\CodifyMe\\src\\main\\java\\edu\\codifyme\\practice\\big.txt")));

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 1000; j++) {
                bw.write(j+",");
            }
            bw.newLine();
        }

        bw.close();
    }
}
