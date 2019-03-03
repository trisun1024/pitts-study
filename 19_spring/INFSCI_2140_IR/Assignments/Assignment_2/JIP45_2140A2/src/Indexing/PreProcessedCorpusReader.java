package Indexing;

import Classes.Path;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PreProcessedCorpusReader {
    private File inputFile;
    private BufferedReader inputBufferedReader;

    public PreProcessedCorpusReader(String type) throws IOException {
        // This constructor opens the pre-processed corpus file, Path.ResultHM1 + type
        // You can use your own version, or download from http://crystal.exp.sis.pitt.edu:8080/iris/resource.jsp
        // Close the file when you do not use it any more
        inputFile = new File(Path.ResultHM1 + type);
        try {
            inputBufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("File is not found, please check your folder!");
        }
    }

    public Map<String, String> NextDocument() throws IOException {
        // read a line for docNo, put into the map with <"DOCNO", docNo>
        // read another line for the content , put into the map with <"CONTENT", content>
        Map<String, String> documentMap = new HashMap<>();
        String line = inputBufferedReader.readLine();
        if (line == null) return null;
        else {
            documentMap.put("DOCNO", line);
            documentMap.put("CONTENT", inputBufferedReader.readLine());
            return documentMap;
        }
    }

}
