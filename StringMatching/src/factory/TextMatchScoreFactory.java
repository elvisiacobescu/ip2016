package factory;

import textAligner.TextAligner;
import textMatchScore.HammingDistance;
import textMatchScore.JaroWinkler;
import textMatchScore.Levenshtein;
import textMatchScore.LongestCommonSubsequence;
import textMatchScore.TextMatchScore;

public class TextMatchScoreFactory extends AbstractFactory{

	@Override 
	public TextMatchScore getTextMatchScore(String algorithm ) {
		if(algorithm == null){
			return null;
		}		

		if(algorithm.equalsIgnoreCase("HammingDistance")){
			return new HammingDistance();

		}else if(algorithm.equalsIgnoreCase("JaroWinkler")){
			return new JaroWinkler();

		}else if(algorithm.equalsIgnoreCase("Levenshtein")){
			return new Levenshtein();
		}
		else if(algorithm.equalsIgnoreCase("LCS")){
			return new LongestCommonSubsequence();
		}

		return null;
	}

	@Override
	TextAligner getTextAligner(String algorithm ) {
		return null;
	}

}
