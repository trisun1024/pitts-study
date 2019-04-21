package Indexing;

import Classes.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MyIndexWriter {
    // I suggest you to write very efficient code here, otherwise, your memory cannot hold our corpus...
    private String indexDocnoPath;
    private String indexPostingListPath;
    private FileWriter indexTermWriter;
    private FileWriter indexDocnoWriter;
    private Map<String, TreeMap<Integer, Integer>> postingList;
    private int docCounter;
    private String type;
    private String typePath;
    public Map<Integer, Integer> fileMapper;

    public MyIndexWriter(String type) throws IOException {
        // This constructor should initiate the FileWriter to output your index files
        // remember to close files if you finish writing the index
        this.type = type;
        if (type.equals("trectext")) {
            typePath = Path.IndexTextDir;
        } else if (type.equals("trecweb")) {
            typePath = Path.IndexWebDir;
        } else {
            throw new IOException("Type error");
        }
        indexDocnoPath = typePath + "docno." + type;
        indexDocnoWriter = new FileWriter(new File(indexDocnoPath));
        indexPostingListPath = typePath + "postinglist." + type;
        indexTermWriter = new FileWriter(new File(indexPostingListPath));
        postingList = new HashMap<>();
        docCounter = 1;
    }

    public void IndexADocument(String docno, String content) throws IOException {
        // you are strongly suggested to build the index by installments
        // you need to assign the new non-negative integer docId to each document, which will be used in MyIndexReader

        // Write docno into a single file
        indexDocnoWriter.write(docno + "\n");
        // Produce content
        String[] contentArray = content.split(" ");
        for (String term : contentArray) {
            // Put content into a HashMap list
            // Create a default TreeMap to store initial docID and freq(0)
            TreeMap<Integer, Integer> initDocFreq = new TreeMap<>();
            initDocFreq.put(docCounter, 0);
            // Set docFreq, if postingList contains term, insert values; or put initial into it
            TreeMap<Integer, Integer> docFreq = postingList.getOrDefault(term, initDocFreq);
            // Put value into docFreq. If docFreq contains current docID then freq number +1; else set to default
            docFreq.put(docCounter, docFreq.getOrDefault(docCounter, 0) + 1);
            // Final, put all relationship between term and docFreq into Map
            postingList.put(term, docFreq);
        }

        // Write the file in each 10000 documents
        if (docCounter % 10000 == 0) {
            partitionFileWriter();
        }
        docCounter++;
    }

    /**
     * partitionFileWriter
     * This is file writer to write file in partition, in order to reduce the pressure of memory
     *
     * @throws IOException
     */
    private void partitionFileWriter() throws IOException {
        for (Map.Entry<String, TreeMap<Integer, Integer>> entry : postingList.entrySet()) {
            String term = entry.getKey();
            Map<Integer, Integer> docFreq = entry.getValue();
            //assign term in the first part
            indexTermWriter.write(term + " ");
            // assign docno as second part, frequency as third part
            for (Integer id : docFreq.keySet()) {
                indexTermWriter.write(id + "#" + docFreq.get(id) + " ");
            }
            indexTermWriter.write("\n");
        }
        postingList.clear();// Release the pressure of memory
    }

    public void Close() throws IOException {
        // close the index writer, and you should output all the buffered content (if any).
        // if you write your index into several files, you need to fuse them here.
        partitionFileWriter();
        indexDocnoWriter.close();
        indexTermWriter.close();
    }

}
