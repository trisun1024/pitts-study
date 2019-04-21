package PreProcessData;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is for INFSCI 2140 in 2019
 * <p>
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
    // Essential private methods or variables can be added.
    private ArrayList<String> textArrayList;
    private int index = 0;

    // YOU MUST IMPLEMENT THIS METHOD.
    public WordTokenizer(char[] texts) {
        // Tokenize the input texts.
        textArrayList = new ArrayList<>();
        String textString = String.valueOf(texts)
                .replaceAll("[/n/t]", "") // remove any new line or tab
                .replaceAll(" {2,}", " ") // remove any space more than 2 into 1 space
                .replaceAll("[-+.^:,\\(\\)\";`_'&@#$|\\[\\]<>]", ""); // remove any of similar special characters
        String[] textInToken = textString.split(" ");
        for (int i = 0; i < textInToken.length; i++) {
            textArrayList.add(textInToken[i].replaceAll(" {2,}", " "));
        }
    }

    // YOU MUST IMPLEMENT THIS METHOD.
    public char[] nextWord() {
        // Return the next word in the document.
        // Return null, if it is the end of the document.
        if (index < textArrayList.size()) {
            return textArrayList.get(index++).replaceAll(" {2,}", " ").toCharArray();
        }
        return null;
    }

}
