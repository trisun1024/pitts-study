package PreProcessData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Path;
import jdk.jshell.execution.Util;

/**
 * This is for INFSCI 2140 in 2018
 */
public class TrecwebCollection implements DocumentCollection {
    // Essential private methods or variables can be added.
    private File fileLoader;
    private BufferedReader bufferLoader;
    private static String DOC_START = "<DOC>";
    private static String DOC_END = "</DOC>";
    private static String DOC_NUM_START = "<DOCNO>";
    private static String DOC_NUM_END = "</DOCNO>";
    private static String DOC_WEB_START = "<DOCHDR>";
    private static String DOC_WEB_END = "</DOCHDR>";

    // YOU SHOULD IMPLEMENT THIS METHOD.
    public TrecwebCollection() throws IOException {
        // 1. Open the file in Path.DataWebDir.
        // 2. Make preparation for function nextDocument().
        // NT: you cannot load the whole corpus into memory!!
        fileLoader = new File(Path.DataWebDir);
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
        // 3. the HTML tags should be removed in document content.

        // input data
        String lineReader;
        while ((lineReader = this.bufferLoader.readLine()) != null) {
            // find doc_start continue
            if (!lineReader.equals(DOC_START)) continue;

            HashMap<String, Object> documents = new HashMap<String, Object>();
            String docID = "";
            StringBuilder docContent = new StringBuilder();

            //find the content before doc_end
            while ((lineReader = this.bufferLoader.readLine()) != null) {
                // find document ID
                if (lineReader.length() > 7 && lineReader.substring(0, 7).equals(DOC_NUM_START)) {
                    docID = lineReader.substring(7, lineReader.length() - 8).trim();
                }
                // <DOCHDR> information end, start content
                if (lineReader.equals(DOC_WEB_END)) {
                    while (!(lineReader = bufferLoader.readLine()).equals(DOC_END)) {
                        // remove any new line or tab before insert
                        docContent.append(lineReader.replaceAll("[/n/t]", "").trim() + " ");
                    }
                    break;
                }
            }

            // remove HTML tags <***>
            String docContentNoHTML = docContent.toString().replaceAll("<.+?>"," ");
            documents.put(docID, docContentNoHTML.toCharArray());
            return documents;
        }
        this.bufferLoader.close();
        return null;
    }
}
