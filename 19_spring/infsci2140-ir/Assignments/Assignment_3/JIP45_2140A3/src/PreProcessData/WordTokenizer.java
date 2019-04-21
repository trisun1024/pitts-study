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
    private char[] charArray;
    private int len;
    private int index;

    // YOU MUST IMPLEMENT THIS METHOD.
    public WordTokenizer(char[] texts) {
        // Tokenize the input texts.
        charArray = texts;
        len = charArray.length;
        index = 0;
    }

    // YOU MUST IMPLEMENT THIS METHOD.
    public char[] nextWord() {
        // Return the next word in the document.
        // Return null, if it is the end of the document.
        String s = "";
        // before reaching the end of text
        if (index < len) {
            // loop through text
            for (int i = index; i < len; i++) {
                index++;
                // exclude space and punctuation
                if (charArray[i] != ' ' && Character.isLetter(charArray[i])) {
                    s = s + charArray[i];
                }
                // tokenize when hitting a space or hitting the end of text
                if ((charArray[i] == ' ' || i == len - 1) && s != "") {
                    char[] e = s.toCharArray();
                    return e;
                }
            }
        }
        return null;
    }

}
