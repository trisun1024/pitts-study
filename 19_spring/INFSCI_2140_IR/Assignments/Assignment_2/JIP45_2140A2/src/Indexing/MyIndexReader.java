package Indexing;

import Classes.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class MyIndexReader {
    //you are suggested to write very efficient code here, otherwise, your memory cannot hold our corpus...
    private String indexDocnoPath;
    private String indexPostingListPath;
    private Map<String, Integer> docnos;
    private Map<String, String> docPostingList;
    private Set<String> terms;
    private String type;
    private String typePath;
    private Integer count;

    public MyIndexReader(String type) throws IOException {
        //read the index files you generated in task 1
        //remember to close them when you finish using them
        //use appropriate structure to store your index
        this.type = type;
        docnos = new HashMap<>();
        docPostingList = new HashMap<>();
        if (type.equals("trectext")) {
            typePath = Path.IndexTextDir;
        } else if (type.equals("trecweb")) {
            typePath = Path.IndexWebDir;
        } else {
            throw new IOException("Type error");
        }
        indexDocnoPath = typePath + "docno." + type;

        // Create HashMap between docno and docID
        FileReader fr = new FileReader(new File(indexDocnoPath));
        BufferedReader br = new BufferedReader(fr);
        String docno = br.readLine();
        int docID = 1;
        while (docno != null) {
            docnos.put(docno, docID++);
            docno = br.readLine();
        }
        fr.close();
        br.close();
    }

    //get the non-negative integer docId for the requested docNo
    //If the requested docno does not exist in the index, return -1
    public int GetDocid(String docno) {
        if (docnos.containsKey(docno)) {
            return docnos.get(docno); // return docID
        }
        return -1;
    }

    // Retrieve the docno for the integer docid
    public String GetDocno(int docid) {
        String result = null;
        Set<Map.Entry<String, Integer>> set = docnos.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            if (entry.getValue().equals(docid)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }

    // Retrieve matching list of doc information
    private void tokenMap() throws IOException {
        indexPostingListPath = typePath + "postinglist." + type;
        FileReader fr = new FileReader(new File(indexPostingListPath));
        BufferedReader br = new BufferedReader(fr);
        String docInfo = br.readLine();
        while (docInfo != null) {
            String[] s = docInfo.split(" ");
            String term = s[0];
            docInfo = docInfo.substring(term.length() + 1, docInfo.length());
            docPostingList.put(term, docPostingList.getOrDefault(term, "") + docInfo);
            docInfo = br.readLine();
        }
        fr.close();
        br.close();
    }

    /**
     * Get the posting list for the requested token.
     * <p>
     * The posting list records the documents' docids the token appears and corresponding frequencies of the term, such as:
     * <p>
     * [docid]		[freq]
     * 1			3
     * 5			7
     * 9			1
     * 13			9
     * <p>
     * ...
     * <p>
     * In the returned 2-dimension array, the first dimension is for each document, and the second dimension records the docid and frequency.
     * <p>
     * For example:
     * array[0][0] records the docid of the first document the token appears.
     * array[0][1] records the frequency of the token in the documents with docid = array[0][0]
     * ...
     * <p>
     * NOTE that the returned posting list array should be ranked by docid from the smallest to the largest.
     *
     * @param token
     * @return
     */
    public int[][] GetPostingList(String token) throws IOException {
        // Load the content containing token
        if(!docPostingList.containsKey(token)) tokenMap();
        // if don't have token return null
        if (!docPostingList.containsKey(token)) return null;
        // Retrieve posting list
        String res = docPostingList.get(token);
        String[] resArray = res.split(" ");
        int[][] result = new int[resArray.length][2];
        for (int i = 0; i < resArray.length; i++) {
            String[] docFreq = resArray[i].split("#");
            result[i][0] = Integer.parseInt(docFreq[0]);
            result[i][1] = Integer.parseInt(docFreq[1]);
        }
        return result;
    }

    // Return the number of documents that contains the token.
    public int GetDocFreq(String token) throws IOException {
        int num = 0;
        // Load the content containing token
        if(!docPostingList.containsKey(token)) tokenMap();
        // if don't have token return null
        if (!docPostingList.containsKey(token)) return num;
        String[] tokenList = docPostingList.get(token).split(" ");
        num = tokenList.length;
        return num;
    }

    // Return the total number of times the token appears in the collection.
    public long GetCollectionFreq(String token) throws IOException {
        long res = 0;
        // Load the content containing token
        if(!docPostingList.containsKey(token)) tokenMap();
        // if don't have token return null
        if (!docPostingList.containsKey(token)) return res;
        String[] tokenList = docPostingList.get(token).split(" ");
        for (int i = 0; i < tokenList.length; i++) {
            String[] docFreq = tokenList[i].split("#");
            res += Integer.parseInt(docFreq[1]);
        }
        return res;
    }

    public void Close() throws IOException {
        docnos.clear();
        docPostingList.clear();
    }

}