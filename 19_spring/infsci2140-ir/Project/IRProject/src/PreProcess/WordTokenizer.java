package PreProcess;

/**
 * This is for INFSCI 2140 in 2019
 * 
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	// Essential private methods or variables can be added.
	
	private char[] char1;
	private int index = 0;
	private int chlength;
	// YOU MUST IMPLEMENT THIS METHOD.
	public WordTokenizer( char[] texts ) {
		// Tokenize the input texts.
		char1 = texts;
         chlength = char1.length;
	}
	
	// YOU MUST IMPLEMENT THIS METHOD.
	public char[] nextWord() {
		// Return the next word in the document.
		// Return null, if it is the end of the document.
     String str = "";
     //loop the whole text and tokenize it, remove spaces and punctuations and leave behind single words
     for(int i = index; i < chlength;i++) {
     if(index < chlength) {
    		 index++;
    		 if(char1[i] != ' ' && Character.isLetter(char1[i]) ) {
    			 str = str + char1[i];
    		 }
    		 if((char1[i] == ' ' || i == chlength - 1) && str != "") {
    			 char[] ch = str.toCharArray();
    			 return ch;
    		 }
    	 }
     }
     return null;
	}
	
}
