package PreProcessData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import Classes.Path;

/**
 * This is for INFSCI 2140 in 2019
 */
public class TrectextCollection implements DocumentCollection {
    // Essential private methods or variables can be added.
    private File fileLoader;
    private BufferedReader bufferLoader;
    private static String DOC_START = "<DOC>";
    private static String DOC_END = "</DOC>";
    private static String DOC_NUM_START = "<DOCNO>";
    private static String DOC_NUM_END = "</DOCNO>";
    private static String DOC_TEXT_START = "<TEXT>";
    private static String DOC_TEXT_END = "</TEXT>";

    // YOU SHOULD IMPLEMENT THIS METHOD.
    public TrectextCollection() throws IOException {
        // 1. Open the file in Path.DataTextDir.
        // 2. Make preparation for function nextDocument().
        // NT: you cannot load the whole corpus into memory!!
        fileLoader = new File(Path.DataTextDir);
        try {
            this.bufferLoader = new BufferedReader(new FileReader(fileLoader));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found! Please check file location");
        }
    }

    // YOU SHOULD IMPLEMENT THIS METHOD.
    public Map<String, Object> nextDocument() throws IOException {
        // 1. When called, this API processes one document from corpus, and returns its doc number and content.
        // 2. When no document left, return null, and close the file.

        String lineReader;
        // input data
        while ((lineReader = this.bufferLoader.readLine()) != null) {
            // find doc_start continue
            if (!lineReader.equals(DOC_START)) continue;

            HashMap<String, Object> documents = new HashMap<String, Object>();
            String docID = "";
            StringBuilder docContent = new StringBuilder();

            //find the content before doc_end
            while (!(lineReader = this.bufferLoader.readLine()).equals(DOC_END)) {
                String[] lineSplitting = lineReader.split(" ");
                // find document ID
                if (lineSplitting[0].equals(DOC_NUM_START)) {
                    docID = lineSplitting[1];
                }
                // content begins
                if (lineReader.equals(DOC_TEXT_START)) {
                    while (!(lineReader = this.bufferLoader.readLine()).equals(DOC_TEXT_END)) {
                        docContent.append(lineReader.trim() + " ");
                    }
                }
            }
            documents.put(docID, docContent.toString().toCharArray());
            return documents;
        }
        this.bufferLoader.close();
        return null;
    }

}
