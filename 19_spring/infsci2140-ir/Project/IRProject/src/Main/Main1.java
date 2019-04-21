package Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.FilterWriter;
import java.util.Map;

import Classes.Path;
import PreProcess.*;

public class Main1 {

    public static void main(String[] args) throws Exception {

        Main1 preprocess = new Main1();
        preprocess.PreProcess("txt");
    }

    public Map<String, Map<Object, Map<Object, Object>>> PreProcess(String dataType) throws Exception {

        DocumentCollection corpus = null;
        if (dataType.equals("txt")) {
            corpus = new ProcessText();
        }


        StopWordRemover stopwordRemover = new StopWordRemover();

        WordNormalizer normalizer = new WordNormalizer();

        FileWriter wr = new FileWriter(Path.preprocessOutput);
        FileWriter wrLink = new FileWriter(Path.docUrlRelation);
        FileWriter wrTitle = new FileWriter(Path.urlTitle);
        FileWriter wrContent = new FileWriter(Path.urlContent);

        //Map<String, Map<Object, Object>> all = null;
        Map<String, Map<Object, Map<Object, Object>>> all = null;

        int count = 0;
        while ((all = corpus.nextDoc()) != null) {

            String docno = all.keySet().iterator().next();
            String url = (String) all.get(docno).keySet().iterator().next();

            wrLink.append(docno + " ");

            for (Object link : all.get(docno).keySet()) {
                String urls = link.toString();
                wr.append(urls + "\n");
                wrLink.append(urls + "\n");
                wrTitle.append(urls + "###");
                wrContent.append(urls + "###");
            }

            for (Object title : all.get(docno).get(url).keySet()) {
                char[] title1 = (char[]) title;
                wrTitle.append(String.valueOf(title1) + "\n");
                WordTokenizer tokenizer1 = new WordTokenizer(title1);
                char[] word1 = null;
                while ((word1 = tokenizer1.nextWord()) != null) {
                    word1 = normalizer.lowercase(word1);
                    if (!stopwordRemover.isStopword(word1)) {
                        wr.append(normalizer.stem(word1) + " ");
                    }
                }
            }
            wr.append(" ");


            Object title = all.get(docno).get(url).keySet().iterator().next();
            char[] text = (char[]) all.get(docno).get(url).get(title);
            wrContent.write(String.valueOf(text) + "\n");
            // Initiate the WordTokenizer class.
            WordTokenizer tokenizer2 = new WordTokenizer(text);
            // Initiate a word object, which can hold a word.
            char[] word2 = null;
            // Process the document word by word iteratively.
            while ((word2 = tokenizer2.nextWord()) != null) {
                // Each word is transformed into lowercase.
                word2 = normalizer.lowercase(word2);
                // Only non-stopword will appear in result file.
                if (!stopwordRemover.isStopword(word2))
                    // Words are stemmed.
                    wr.append(normalizer.stem(word2) + " ");
            }
            wr.append("\n");// Finish processing one document.
            count++;
        }
        System.out.println("total document count:  " + count);
        wr.close();
        wrLink.close();
        wrTitle.close();
        wrContent.close();

        return all;
    }
}
