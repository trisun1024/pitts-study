package PreProcessData;

import Classes.*;

/**
 * This is for INFSCI 2140 in 2018
 */
public class WordNormalizer {
    // Essential private methods or variables can be added.

    // YOU MUST IMPLEMENT THIS METHOD.
    public char[] lowercase(char[] chars) {
        // Transform the word uppercase characters into lowercase.
        return String.valueOf(chars).toLowerCase().toCharArray();
    }

    // YOU MUST IMPLEMENT THIS METHOD.
    public String stem(char[] chars) {
        // Return the stemmed word with Stemmer in Classes package.
        Stemmer stm = new Stemmer();
        stm.add(chars,chars.length);
        stm.stem();
        return stm.toString();
    }

}
