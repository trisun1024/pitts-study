package Search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.Document;
import Classes.Query;
import Index.indexReader;

public class QueryRetrievalModel {

    protected indexReader indexReader;
    private long colLength;
    private double miu = 2000;
    private int docLength;
    double score = 1;
    int[][] postinglist;


    private double getScore(int docid, double score, String token, int docFreq) throws IOException {
        long colFreq = indexReader.CollectionFreq(token);
        docLength = indexReader.docLength(docid);

        score *= ((docFreq + miu * (colFreq / colLength)) / (docLength + miu));
        return score;
    }

    public QueryRetrievalModel(indexReader ixreader) throws IOException {
        indexReader = ixreader;
        colLength = ixreader.getColLength();
    }

    /**
     * Search for the topic information.
     * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
     * TopN specifies the maximum number of results to be returned.
     *
     * @param aQuery The query to be searched for.
     * @param TopN   The maximum number of returned document
     * @return
     */

    public List<Document> retrieveQuery(Query aQuery, int TopN) throws IOException {
        // NT: you will find our IndexingLucene.Myindexreader provides method: docLength()
        // implement your retrieval model here, and for each input query, return the topN retrieved documents
        // sort the docs based on their relevance score, from high to low
        String[] tokens = aQuery.GetQueryContent().split(" ");
        Map<Integer, Double> docScore = new HashMap<>();//store the scores in a hashmap: docid as key, score as value;

        for (String token : tokens) {
            if (token.length() == 0) continue;
            postinglist = indexReader.getPostingList(token);
            if (postinglist == null) continue;
            for (int i = 0; i < postinglist.length; i++) {
                int docId = postinglist[i][0];
                int docFreq = postinglist[i][1];
                double score = docScore.getOrDefault(docId, 1.0);
                score = getScore(docId, score, token, docFreq);
                docScore.put(docId, score);
            }

        }

        List<Document> docs = new ArrayList<>();
        for (int docid : docScore.keySet()) {
            Document doc = new Document(Integer.toString(docid), indexReader.getDocno(docid), docScore.get(docid));
            docs.add(doc);
        }

        //Rank the results by the score from the most relevant to the least
        Collections.sort(docs, Collections.reverseOrder(new Comparator<Document>() {

            @Override
            public int compare(Document d1, Document d2) {

                return new Double(d1.score()).compareTo(new Double(d2.score()));
            }
        }));
        if (!(docs.size() == 0)) {
            List<Document> docTopN = new ArrayList<>();
            for (int i = 0; i < TopN; i++) {
                docTopN.add(docs.get(i));
            }
            return docTopN;
        }
        return null;
    }
}
