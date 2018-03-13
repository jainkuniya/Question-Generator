package btp;



import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

class demo {

		static String sub;
		static String filename;
  /*
   * The main method demonstrates the easiest way to load a parser.
   * Simply call loadModel and specify the path of a serialized grammar
   * model, which can be a file, a resource on the classpath, or even a URL.
   * For example, this demonstrates loading a grammar from the models jar
   * file, which you therefore need to include on the classpath for ParserDemo
   * to work.
   *
   * Usage: {@code java ParserDemo [[model] textFile]}
   * e.g.: java ParserDemo edu/stanford/nlp/models/lexparser/chineseFactored.ser.gz data/chinese-onesent-utf8.txt
   *
   */
  public static void main(String[] args) {
    String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

  /*  if (args.length > 0) {
      parserModel = args[0];
    }*/
    LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);

    if (args.length == 0) {
      demoAPI(lp);
    } else {
      String textFile = (args.length > 1) ? args[1] : args[0];
      filename=textFile;
      demoDP(lp, textFile);
    }
  }

  /**
   * demoDP demonstrates turning a file into tokens and then parse
   * trees.  Note that the trees are printed by calling pennPrint on
   * the Tree object.  It is also possible to pass a PrintWriter to
   * pennPrint if you want to capture the output.
   * This code will work with any supported language.
   */
  public static void demoDP(LexicalizedParser lp, String filename) {
    // This option shows loading, sentence-segmenting and tokenizing
    // a file using DocumentPreprocessor.
    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
    GrammaticalStructureFactory gsf = null;
    if (tlp.supportsGrammaticalStructures()) {
      gsf = tlp.grammaticalStructureFactory();
    }
    // You could also create a tokenizer here (as below) and pass it
    // to DocumentPreprocessor
    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
      Tree parse = lp.apply(sentence);
     // parse.pennPrint();
     // System.out.println();

      if (gsf != null) {
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        ArrayList<TypedDependency> tdl =(ArrayList) gs.typedDependenciesCCprocessed();
        System.out.println(tdl);
        System.out.println();
        formatOutput(tdl);
      }
    }
  }

  /**
   * demoAPI demonstrates other ways of calling the parser with
   * already tokenized text, or in some cases, raw text that needs to
   * be tokenized as a single sentence.  Output is handled with a
   * TreePrint object.  Note that the options used when creating the
   * TreePrint can determine what results to print out.  Once again,
   * one can capture the output by passing a PrintWriter to
   * TreePrint.printTree. This code is for English.
   */
  public static void demoAPI(LexicalizedParser lp) {
    // This option shows parsing a list of correctly tokenized words
	  Object[] a;
    String[] sent = { "ram", "is", "an", "easy", "sentence", "." };
    List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(sent);
    Tree parse = lp.apply(rawWords);
    //parse.pennPrint();
    //System.out.println();

    // This option shows loading and using an explicit tokenizer
    String sent2 = "ram is another sentence.";
    TokenizerFactory<CoreLabel> tokenizerFactory =
        PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
    Tokenizer<CoreLabel> tok =
        tokenizerFactory.getTokenizer(new StringReader(sent2));
    List<CoreLabel> rawWords2 = tok.tokenize();
    parse = lp.apply(rawWords2);

    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // PennTreebankLanguagePack for English
    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
    GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
    ArrayList<TypedDependency> tdl = (ArrayList)gs.typedDependencies();

 
    System.out.println(tdl);
    System.out.println();
    formatOutput(tdl);
    // You can also use a TreePrint object to print trees and dependencies
    TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
    //tp.printTree(parse);
  }

  private demo() {} // static methods only

  static public void formatOutput( List<TypedDependency> tdl){
	  for(int i=0;i<tdl.size();i++){
		  if(tdl.get(i).reln().getShortName().equals("nsubj")){
			  //System.out.println(convertToString(tdl.get(i).dep()));
			  sub=convertToString(tdl.get(i).dep());
			  generateOutput(filename);
		  }
	  }
	  
  }
  static public String convertToString(IndexedWord word){
	  String result=null;
	  result=word.toString().split("/")[0];
	  return result;
  }
  static public void generateOutput(String filename){
	  String line;
	  try {
		InputStream fr=new FileInputStream(filename);
		InputStreamReader isr=new InputStreamReader(fr,Charset.forName("UTF-8"));
		BufferedReader br=new BufferedReader(isr);
		try {
			while((line=br.readLine())!=null){
				String[] words=line.split(" ");
				for(int i=0;i<words.length;i++){
					if(words[i].equals(sub)){
						System.out.print("--"+" ");
					}
					else{
						System.out.print(words[i]+" ");
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
