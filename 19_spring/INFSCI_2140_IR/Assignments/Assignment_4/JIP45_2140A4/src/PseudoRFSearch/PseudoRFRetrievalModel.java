package PseudoRFSearch;

import java.util.*;

import Classes.Document;
import Classes.Query;
import IndexingLucene.MyIndexReader;
import SearchLucene.QueryRetrievalModel;

public class PseudoRFRetrievalModel {

    MyIndexReader ixreader;
    QueryRetrievalModel qrModel;
    private List<Document> documentListTopK;
    private String[] tokenList;
    private HashSet<Integer> docIDList;
    private double MU;
    private final long LENGTH_OF_COLLECTION;

    public PseudoRFRetrievalModel(MyIndexReader ixreader) {
        this.ixreader = ixreader;
        LENGTH_OF_COLLECTION = ixreader.getLengthOfCollection();
    }

    /**
     * Search for the topic with pseudo relevance feedback in 2017 spring assignment 4.
     * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
     *
     * @param aQuery The query to be searched for.
     * @param TopN   The maximum number of returned document
     * @param TopK   The count of feedback documents
     * @param alpha  parameter of relevance feedback model
     * @return TopN most relevant document, in List structure
     */
    public List<Document> RetrieveQuery(Query aQuery, int TopN, int TopK, double alpha) throws Exception {
        // this method will return the retrieval result of the given Query, and this result is enhanced with pseudo relevance feedback
        // (1) you should first use the original retrieval model to get TopK documents, which will be regarded as feedback documents
        // (2) implement GetTokenRFScore to get each query token's P(token|feedback model) in feedback documents
        // (3) implement the relevance feedback model for each token: combine the each query token's original
        // retrieval score P(token|document) with its score in feedback documents P(token|feedback model)
        // (4) for each document, use the query likelihood language model to get the whole query's new score,
        // P(Q|document)=P(token_1|document')*P(token_2|document')*...*P(token_n|document')

        // (1)
        // obtain retrieval model to get TopK documents
        qrModel = new QueryRetrievalModel(ixreader);
        documentListTopK = qrModel.retrieveQuery(aQuery, TopK);

        // (2)
        // convert query to tokens
        tokenList = aQuery.GetQueryContent().split(" ");
        // setup initiates
        docIDList = new HashSet<>();
        //get P(token|feedback documents)
        HashMap<String, Double> TokenRFScore = GetTokenRFScore(aQuery, TopK);

        // reload informations into tokenDocFreqList
        Map<String, Map<Integer, Integer>> tokenDocFreqList = new HashMap<>();
        for (String token : tokenList) {
            // if a token has never appeared in the whole collection, remove it
            if (!TokenRFScore.containsKey(token)) continue;
            // if this token has been processed
            if (tokenDocFreqList.containsKey(token)) continue;
            Map<Integer, Integer> docFre = new HashMap<>();
            int[][] postingList = ixreader.getPostingList(token);
            for (int[] row : postingList) {
                int docId = row[0];
                int docF = row[1];
                // if the doc is not in document list
                if (!docIDList.contains(docId)) continue;
                docFre.put(docId, docF);
            }
            tokenDocFreqList.put(token, docFre);
        }
        // System.out.println(tokenDocFreqList.size());

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

        // (3) & (4)
        for (Integer id : docIDList) {
            double score = 1.0;
            for (String token : tokenList) {
                if (!TokenRFScore.containsKey(token)) continue;
                double tokenDoc ;
                if (!tokenDocFreqList.get(token).containsKey(id)) tokenDoc = 0.0;
                else tokenDoc = (double) tokenDocFreqList.get(token).get(id);
                double tokenDocProbability = tokenDoc / (double) ixreader.docLength(id);
                double tokenFeedbackProbability = TokenRFScore.get(token);
                double tokenScore = alpha * tokenDocProbability + (1 - alpha) * tokenFeedbackProbability;
                score *= tokenScore;
            }

            // get Top-N
            if (topN.size() < TopN) {
                Document document = new Document(Integer.toString(id), ixreader.getDocno(id), score);
                topN.add(document);
            } else if (score > topN.peek().score()) {
                topN.poll();
                Document document = new Document(Integer.toString(id), ixreader.getDocno(id), score);
                topN.add(document);
            }
        }

        // sort all retrieved documents from most relevant to least, and return TopN
        List<Document> results = new ArrayList<>();
        for (int i = 0; i < TopN; i++) {
            results.add(0, topN.poll());
        }
        return results;
    }

    public HashMap<String, Double> GetTokenRFScore(Query aQuery, int TopK) throws Exception {
        // for each token in the query, you should calculate token's score in feedback documents: P(token|feedback documents)
        // use Dirichlet smoothing
        // save <token, score> in HashMap TokenRFScore, and return it
        HashMap<String, Double> TokenRFScore = new HashMap<String, Double>();

        int docLength = 0;
        HashMap<String, Integer> pseudoDoc = new HashMap<>();
        for (Document doc : documentListTopK) {
            int docID = Integer.parseInt(doc.docid());
            docIDList.add(docID);
        }

        // initial score
        double score = 1;
        // let miu as the average doc length of this collection
        MU = (double) LENGTH_OF_COLLECTION / (double) docLength ;
        // compute the coefficient:  miu + |D|
        double ceof1 = MU + (double) docLength;
        // compute another coefficient: miu / (|D| + miu)
        double ceof2 = MU / ceof1;

        for (String token : tokenList) {
            long tokenFinalFreq = ixreader.CollectionFreq(token);
            if (tokenFinalFreq == 0) continue;
            int[][] postingList = ixreader.getPostingList(token);
            int tokenDocFreq = 0;
            for (int[] i : postingList) {
                if (docIDList.contains(i[0])) tokenDocFreq += i[1];
            }
            // use Dirichlet smoothing
            // p(w|D) = (|D|/(|D|+MU))*(c(w,D)/|D|) + (MU/(|D|+MU))*p(w|REF)
            // score c1*p_doc + c2*p_ref
            long cf = tokenFinalFreq;
            int tf = tokenDocFreq;
            double p_doc = (double) tf / docLength; // c(w, D)
            double p_ref = (double) cf / LENGTH_OF_COLLECTION; // p(w|REF)
            score *= (ceof1 * p_doc + ceof2 * p_ref); // the probability is multiplied to the score
            TokenRFScore.put(token, score);
        }
        return TokenRFScore;
    }


}