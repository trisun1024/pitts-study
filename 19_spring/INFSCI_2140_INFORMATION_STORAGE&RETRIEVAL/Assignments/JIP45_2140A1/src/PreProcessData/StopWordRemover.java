package PreProcessData;

import Classes.*;

import java.io.*;
import java.util.HashSet;

public class StopWordRemover {
    // Essential private methods or variables can be added.
    private HashSet stopWords;

    // YOU SHOULD IMPLEMENT THIS METHOD.
    public StopWordRemover() throws IOException {
        // Load and store the stop words from the fileinputstream with appropriate data structure.
        // NT: address of stopword.txt is Path.StopwordDir

        File fileLoader = new File(Path.StopwordDir);
        BufferedReader bufferLoader = new BufferedReader(new FileReader(fileLoader));
        // import stop words into a HashSet
        this.stopWords = new HashSet<String>();
        String lineReader;
        while ((lineReader = bufferLoader.readLine()) != null) {
            this.stopWords.add(lineReader.trim());
        }
        bufferLoader.close();
    }

    // YOU SHOULD IMPLEMENT THIS METHOD.
    public boolean isStopword(char[] word) {
        // Return true if the input word is a stopword, or false if not.
        if (this.stopWords.contains(String.valueOf(word).trim())) {
            return true;
        }
        return false;
    }

}
