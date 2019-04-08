package Search;

import java.io.IOException;
import java.util.*;

import Classes.Query;
import Classes.Document;
import IndexingLucene.MyIndexReader;

public class QueryRetrievalModel {

    protected MyIndexReader indexReader;
    private double MU = 2000;
    private final long LENGTH_OF_COLLECTION;

    public QueryRetrievalModel(MyIndexReader ixreader) {
        indexReader = ixreader;
        this.LENGTH_OF_COLLECTION = ixreader.getLengthOfCollection();
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
        // sort the documents based on their relevance score, from high to low
        String[] queryContent = aQuery.GetQueryContent().split(" ");

        // contain query token's frequency
        Map<String, Long> tokenFreqSum = new HashMap<>();

        // contain document posting list
        Map<Integer, Map<String, Integer>> queryResult = new HashMap<>();

        // add token frequency into the map
        for (String token : queryContent) {
            // check token if exists in map or not in total collection, if so, skip
            if (tokenFreqSum.containsKey(token) || indexReader.CollectionFreq(token) == 0) continue;
            // if not, put into map
            tokenFreqSum.put(token, indexReader.CollectionFreq(token));
            // get posting list
            int[][] postingList = indexReader.getPostingList(token);
            // put posting list into map
            for (int[] docPostingList : postingList) {
                int docId = docPostingList[0];
                int tokenFreq = docPostingList[1];
                if (!queryResult.containsKey(docId)) {
                    Map<String, Integer> docFreq = new HashMap<>();
                    docFreq.put(token, tokenFreq);
                    queryResult.put(docId, docFreq);
                } else {
                    Map<String, Integer> docFreq = queryResult.get(docId);
                    docFreq.put(token, tokenFreq);
                }
            }
        }

        Comparator<Document> comparator = new Comparator<Document>() {
            @Override
            public int compare(Document doc1, Document doc2) {
                if (doc1.score() < doc2.score()) return -1;
                if (doc1.score() > doc2.score()) return 1;
                return 0;
            }
        };
        // use Heap to get Top-N for better performance
        PriorityQueue<Document> topN = new PriorityQueue<>(TopN, comparator);

        // query likelihood model, calculate the probability of each document, generating each query terms
        for (Integer docId : queryResult.keySet()) {
            Map<String, Integer> docFreq = queryResult.get(docId);
            int docLength = 0;
            try {
                docLength = indexReader.docLength(docId);
            } catch (Exception e) {
            }
            ;
            // initial score
            double score = 1;
            // let miu as the average doc length of this collection
            MU = (double) docLength / (double) LENGTH_OF_COLLECTION;
            // compute the coefficient:  miu + |D|
            double ceof1 = docLength / (docLength + MU);
            // compute another coefficient: miu / (|D| + miu)
            double ceof2 = MU / (docLength + MU);
            // iterate every token in the query
            for (String queryToken : queryContent) {
                // if token is not exists in total collection then delete
                if (!tokenFreqSum.containsKey(queryToken)) continue;
                // else calculate the score based on Dirichlet Prior Smoothing function
                // p(w|D) = (|D|/(|D|+MU))*(c(w,D)/|D|) + (MU/(|D|+MU))*p(w|REF)
                // score c1*p_doc + c2*p_ref
                long cf = tokenFreqSum.get(queryToken);
                int tf = docFreq.getOrDefault(queryToken, 0);
                double p_doc = (double) tf / docLength; // c(w, D)
                double p_ref = (double) cf / LENGTH_OF_COLLECTION; // p(w|REF)
                score *= (ceof1 * p_doc + ceof2 * p_ref); // the probability is multiplied to the score
            }

            // get Top-N
            if (topN.size() < TopN) {
                Document document = new Document(Integer.toString(docId), indexReader
                        .getDocno(docId), score);
                topN.add(document);
            } else if (score > topN.peek().score()) {
                topN.poll();
                Document document = new Document(Integer.toString(docId), indexReader
                        .getDocno(docId), score);
                topN.add(document);
            }
        }

        // add topN documents to the result list
        List<Document> result = new ArrayList<>();
        for (int i = 0; i < TopN; i++) {
            result.add(0, topN.poll());
        }
        return result;
    }

}