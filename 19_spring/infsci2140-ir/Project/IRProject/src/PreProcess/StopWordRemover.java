package PreProcess;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import Classes.*;

public class StopWordRemover {
	// Essential private methods or variables can be added.
	
    private BufferedReader treader;
    private HashSet<String> stopword = null;
    
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public StopWordRemover() throws IOException {
		// Load and store the stop words from the fileinputstream with appropriate data structure.
		// NT: address of stopword.txt is Path.StopwordDir
	      String line = "";
	    //Use FileInputStream and BufferedReader to load the stopwords file.
	      FileInputStream freader = new FileInputStream(Path.stopwords);
	      treader = new BufferedReader(new InputStreamReader(freader));
	    //I use hashset to store the stop words
	      stopword = new HashSet<String>();
	      while((line = treader.readLine()) != null) {
               stopword.add(line);
	      }
	}

	// YOU SHOULD IMPLEMENT THIS METHOD.
	public boolean isStopword( char[] word ){
		// Return true if the input word is a stopword, or false if not.
		if(stopword.contains(String.valueOf(word))) 
			return true;
		    return false;
	}
}
