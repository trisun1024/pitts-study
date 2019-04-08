package Search;

import Classes.Path;
import Classes.Query;
import PreProcessData.StopWordRemover;
import PreProcessData.WordNormalizer;
import PreProcessData.WordTokenizer;

import java.io.*;
import java.util.ArrayList;

public class ExtractQuery {

    private BufferedReader br;
    private ArrayList<Query> queryList;
    private int index;

    public ExtractQuery() throws Exception {
        //you should extract the 4 queries from the Path.TopicDir
        //NT: the query content of each topic should be 1) tokenized, 2) to lowercase, 3) remove stop words, 4) stemming
        //NT: you can simply pick up title only for query, or you can also use title + description + narrative for the query content.

        // initiate the reader to the file
        File topics = new File(Path.TopicDir);
        br = new BufferedReader(new FileReader(topics));
        // read topics into queryList
        index = 0;
        queryList = new ArrayList<>();
        Query query = new Query();
        String queryTitle = "";
        String line;
        // input data
        while ((line = br.readLine()) != null) {
            // set topicId
            if (line.length() >= 14 && line.substring(0, 5).equals("<num>")) {
                query.SetTopicId(line.substring(14, line.length()));
            }
            // set title
            if (line.length() >= 7 && line.substring(0, 7).equals("<title>")) {
                queryTitle += PreProcess(line.substring(7, line.length()));
                query.SetQueryContent(queryTitle);
                queryList.add(query);
                query = new Query();
                queryTitle = "";
            }
        }
        br.close();
    }

    public boolean hasNext() throws IOException {
        if (index < queryList.size()) return true;
        return false;
    }

    public Query next() {
        return queryList.get(index++);
    }

    private String PreProcess(String line) throws Exception {
        String result = "";
        if (line == null || line.length() == 0) return result;
        // tokenize, normalize, remove stop words, stemming
        // Loading stopword, and initiate StopWordRemover.
        StopWordRemover stopwordRemover = new StopWordRemover();

        // Initiate WordNormalizer.
        WordNormalizer normalizer = new WordNormalizer();

        // Initiate the WordTokenizer class.
        WordTokenizer wordTokenizer = new WordTokenizer(line.toCharArray());

        // Initiate a word object
        char[] word = null;

        // Process the document word iteratively
        while ((word = wordTokenizer.nextWord()) != null) {
            // Each word is transformed into lowercase.
            word = normalizer.lowercase(word);
            // Only non-stopword will appear in result life
            if (!stopwordRemover.isStopword(word)) {
                // Words are stemmed.
                result += normalizer.stem(word) + " ";
            }
        }
        return result;
    }
}
