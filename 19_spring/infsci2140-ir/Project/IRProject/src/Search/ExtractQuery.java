package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Classes.*;
import PreProcess.StopWordRemover;
import PreProcess.WordNormalizer;
import PreProcess.WordTokenizer;

public class ExtractQuery {

    private BufferedReader breader;
    private FileReader freader;
    Query query;
    private ArrayList<String> titles = new ArrayList<String>();

    int tCount = 0;
    private String input;

    public ExtractQuery(String input) throws IOException {
        this.input = input;
        String afterProcessTitile = preProcess(input);
        titles.add(afterProcessTitile);
    }

    public boolean hasNext()
    {
        if(tCount < titles.size()) {
            tCount++;
            return true;
        }
        return false;
    }

    public Query next()
    {
        query = new Query();
        query.SetQueryContent(titles.get(tCount-1));
        return query;
    }


    public String preProcess(String content) throws IOException {

        StringBuffer sBuffer = new StringBuffer();
        char[] conArray = content.toCharArray();
        char[] word = null;
        WordTokenizer tokenizer = new WordTokenizer(conArray);
        WordNormalizer Normalizer = new WordNormalizer();
        StopWordRemover StopWordRemover = new StopWordRemover();
        while((word = tokenizer.nextWord()) != null) {
            word = Normalizer.lowercase(word);
            if(!StopWordRemover.isStopword(word)) {
                sBuffer.append(Normalizer.stem(word) + " ");
            }
        }

        String preProcessedContent = new String(sBuffer);
        return preProcessedContent;

    }
}
