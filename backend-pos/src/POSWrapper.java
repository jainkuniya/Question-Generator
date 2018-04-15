import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class POSWrapper {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
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

		JSONObject output = new JSONObject();
		output.put("sentence_count", sentences.size());
		
		//read file 
		String para = FileUtils.readLineByLineJava8(args[0]);
		
		output.put("period_count", para.chars().filter(num -> num == '.').count());
		
		JSONObject pos = new JSONObject();
		
		for (int i=0; i<sentences.size(); i++) {
			List<HasWord> sentence = sentences.get(i);
			List<TaggedWord> tSentence = tagger.tagSentence(sentence);
			
			JSONObject sentenceObj = new JSONObject();
			sentenceObj.put("words_count", tSentence.size());
			
			String completeSentence = "";
			
			JSONObject wordsObj = new JSONObject();
			
			for(int j=0; j<tSentence.size(); j++ ){
				TaggedWord taggedWord = tSentence.get(j);
				completeSentence += taggedWord.word() + " ";
				
				JSONObject  posObj = new JSONObject();
				posObj.put("tag", taggedWord.tag());
				posObj.put("word", taggedWord.word());
				
				wordsObj.put(j, posObj);
				
				System.out.println(taggedWord.tag()+" " + taggedWord.word());
			}
			
			sentenceObj.put("sentence", completeSentence);
			sentenceObj.put("pos", wordsObj);
			
			pos.put(i, sentenceObj);
		}
		
		output.put("pos", pos);

		new FileUtils().createOutputFile(args[1], output.toJSONString());

	}
}