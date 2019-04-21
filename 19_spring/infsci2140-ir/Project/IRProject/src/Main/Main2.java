package Main;

import java.util.Map;

import Index.indexReader;
import Index.indexWriter;
import Index.preProcessedCorpusReader;

public class Main2 {

	public static void main(String[] args) throws Exception {
		Main2 hm2 = new Main2();
		hm2.WriteIndex("txt");

		Long startTime=System.currentTimeMillis();
		hm2.ReadIndex("txt", "phobia");
		Long endTime=System.currentTimeMillis();
		System.out.println("load index & retrieve running time: "+(endTime-startTime)/60000.0+" min");
		
	}

	public void WriteIndex(String dataType) throws Exception {
		// Initiate pre-processed collection file reader
		preProcessedCorpusReader corpus=new preProcessedCorpusReader(dataType);
		
		// initiate the output object
		indexWriter output=new indexWriter(dataType);
		
		// initiate a doc object, which can hold document number and document content of a document
		Map<String, String> doc = null;

		int count=0;
		// build index of corpus document by document
		while ((doc = corpus.nextDocument()) != null) {
			// load document number and content of the document
			String docno = doc.keySet().iterator().next();
			String content = doc.get(docno);
			// index this document
			output.index(docno, content); 
			
			count++;
		}
		System.out.println("total document count:  "+count);
		output.close();
	}
	
	public void ReadIndex(String dataType, String token) throws Exception {
		// Initiate the index file reader
		indexReader ixreader=new indexReader(dataType);
		
		// do retrieval
		int df = ixreader.DocFreq(token);
		long ctf = ixreader.CollectionFreq(token);
		System.out.println(" >> the token \""+token+"\" appeared in "+df+" documents and "+ctf+" times in total");
		if(df>0){
			int[][] posting = ixreader.getPostingList(token);
			for(int ix=0;ix<posting.length;ix++){
				int docid = posting[ix][0];
				int freq = posting[ix][1];
				String docno = ixreader.getDocno(docid);
				System.out.printf("    %20s    %6d    %6d\n", docno, docid, freq);
			}
		}
		ixreader.close();
	}

}
