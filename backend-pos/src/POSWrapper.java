import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSWrapper {
  
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Invalid Params: Include input file path");
			return;
		}
		// try to read file new FileReader("../backend-pos/sample-input.txt")
		FileReader fileReader;
		try {
			fileReader = new FileReader(args[0]);
		}catch (FileNotFoundException e) {
			System.out.println("Invalid input file path");
			return;
		}
	    MaxentTagger tagger = new MaxentTagger("../backend-pos/models/english-left3words-distsim.tagger");
	    List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(fileReader));
	    for (List<HasWord> sentence : sentences) {
	      List<TaggedWord> tSentence = tagger.tagSentence(sentence);
	      System.out.println("\nStarting new line");
	      for (TaggedWord taggedWord: tSentence) {
	    	  System.out.println(taggedWord.tag()+" " + taggedWord.word());
	      }
	    }	
	}
}