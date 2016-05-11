import java.util.ArrayList;
import java.util.List;

import factory.AbstractFactory;
import factory.FactoryProducer;
import factory.TextMatchScoreFactory;
import parser.ParserXMLFile;
import textAligner.TextAligner;
import textMatchScore.TextMatchScore;

public class Match {

	private ParserXMLFile parser;
	private List<String> paragraphsFromFirstText;
	private List<String> paragraphsFromSecondText;

	public Match(String file1, String file2){
		parser = new ParserXMLFile(file1);
		paragraphsFromFirstText = new ArrayList<String>();
		paragraphsFromFirstText = parser.getParagraphs();
		

		parser = new ParserXMLFile(file2);
		paragraphsFromSecondText = new ArrayList<String>();
		paragraphsFromSecondText = parser.getParagraphs();
	}
	
	public double runAlgWithScore(String str1, String str2){
		//get score factory
		AbstractFactory scoreFactory = FactoryProducer.getFactory("SCORE");
		double[] scores = new double[5];

		//get an object of TextMatchScoreFactory
		TextMatchScore hamming = scoreFactory.getTextMatchScore("HammingDistance",str1,str2);
		double hammingScore = hamming.getScore();
		scores[0] = hammingScore;

		//get an object of TextMatchScoreFactory
		TextMatchScore jaroWinkler = scoreFactory.getTextMatchScore("JaroWinkler",str1,str2);
		double jaroWinklerScore = jaroWinkler.getScore();
		scores[1] = jaroWinklerScore;
		
		//get an object of TextMatchScoreFactory
		TextMatchScore levenshtein = scoreFactory.getTextMatchScore("Levenshtein",str1,str2);
		double levenshteinScore = levenshtein.getScore();
		scores[2] = levenshteinScore;
		
		//get an object of TextMatchScoreFactory
		TextMatchScore lcs = scoreFactory.getTextMatchScore("LCS",str1,str2);
		double lcsScore = lcs.getScore();
		scores[3] = lcsScore;
		
		return getMaxFromArrayOfDouble(scores);
	}
	
	public double runAlgWithAlign(String str1, String str2){
		double[] scores = new double[3];
		//get align factory
		AbstractFactory alignFactory = FactoryProducer.getFactory("ALIGN");

		//get an object of TextAlignerFactory
		TextAligner swg = alignFactory.getTextAligner("SWG",str1,str2);
		swg.align();
		scores[0] = this.runAlgWithScore(str1,str2);

		//get an object of TextAlignerFactory
		TextAligner nw = alignFactory.getTextAligner("NW",str1,str2);
		nw.align();
		scores[1] = this.runAlgWithScore(str1,str2);
		
		//get an object of TextAlignerFactory
		TextAligner kmp = alignFactory.getTextAligner("KMP",str1,str2);
		kmp.align();
		scores[2] = this.runAlgWithScore(str1,str2);
		
		return getMaxFromArrayOfDouble(scores);
	}
	
	public double getMaxFromArrayOfDouble(double[] arr){
		double max=0;
		for( int i = 0; i < arr.length; ++i){
			if( arr[i] > max)
				max = arr[i];
		}
		return max;
	}

	public void runAlg(){
		for( int i=0; i < paragraphsFromFirstText.size(); ++i){
			double score = runAlgWithAlign(paragraphsFromFirstText.get(i),paragraphsFromSecondText.get(i));
		}
	}
	
}
