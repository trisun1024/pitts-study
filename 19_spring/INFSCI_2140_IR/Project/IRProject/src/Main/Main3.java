package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Classes.Document;
import Classes.Path;
import Classes.Query;
import Index.indexReader;
import Search.ExtractQuery;
import Search.QueryRetrievalModel;

public class Main3 {

    public static void main(String[] args) throws IOException {
		
/*		System.out.println("Input your query" + "\n");
		Scanner sc = new Scanner(System.in);
		FileWriter fw = new FileWriter(Path.queries);
		BufferedWriter bw = new BufferedWriter(fw);
	    String query = sc.nextLine();
	    while(query != null) {
	    bw.write(query + "\n");
	    }
	    sc.close();
	    fw.close();
	    bw.close();*/
        indexReader ixreader = new indexReader("txt");
        QueryRetrievalModel model = new QueryRetrievalModel(ixreader);
        String input = "social phobia";
        ExtractQuery queries = new ExtractQuery(input);

        while(queries.hasNext()){
            Query aQuery = queries.next();
            List<Document> results = model.retrieveQuery(aQuery, 10);
            if (results != null) {
                int rank = 1;
                for (Document result : results) {
                    System.out.println( result.docno() + " " + rank + " " + result.score());
                    rank++;
                }
            }
        }
        ixreader.close();
    }

}
