package ass1;

import java.io.*;
import java.util.*;

public class ExternalMergeSort {
    public static List<File> createSortedChunks(File inputFile, int chunkSize) throws IOException {
        List<File> chunks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
            if (lines.size() == chunkSize) {
                Collections.sort(lines);
                File temp = File.createTempFile("chunk", ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                for (String s : lines) {
                    bw.write(s);
                    bw.newLine();
                }
                bw.close();
                chunks.add(temp);
                lines.clear();
            }
        }
        if (!lines.isEmpty()) {
            Collections.sort(lines);
            File temp = File.createTempFile("chunk", ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            for (String s : lines) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            chunks.add(temp);
        }
        br.close();
        return chunks;
    }
    public static void main(String[] args) {
        System.out.println("External Merge Sort is used for very large files.");
        System.out.println("1. Divide the file into smaller chunks.");
        System.out.println("2. Sort each chunk individually.");
        System.out.println("3. Store sorted chunks in temporary files.");
        System.out.println("4. Merge all sorted chunks into one final sorted file.");
    }
}

