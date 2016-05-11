package factory;

import textAligner.TextAligner;
import textMatchScore.HammingDistance;
import textMatchScore.JaroWinkler;
import textMatchScore.Levenshtein;
import textMatchScore.LongestCommonSubsequence;
import textMatchScore.TextMatchScore;

public class TextMatchScoreFactory extends AbstractFactory{

	@Override
	public TextMatchScore getTextMatchScore(String algorithm,String str1, String str2 ) {
		if(algorithm == null){
			return null;
		}		

		if(algorithm.equalsIgnoreCase("HammingDistance")){
			return new HammingDistance(str1, str2);

		}else if(algorithm.equalsIgnoreCase("JaroWinkler")){
			return new JaroWinkler(str1, str2);

		}else if(algorithm.equalsIgnoreCase("Levenshtein")){
			return new Levenshtein(str1, str2);
		}
		else if(algorithm.equalsIgnoreCase("LCS")){
			return new LongestCommonSubsequence(str1, str2);
		}

		return null;
	}

	@Override
	public TextAligner getTextAligner(String algorithm,String str1, String str2 ) {
		return null;
	}

}
