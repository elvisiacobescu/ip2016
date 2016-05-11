package factory;


import textAligner.*;
import textMatchScore.HammingDistance;
import textMatchScore.JaroWinkler;
import textMatchScore.Levenshtein;
import textMatchScore.LongestCommonSubsequence;
import textMatchScore.TextMatchScore;

public class TextAlignerFactory extends AbstractFactory{

	@Override
	public TextMatchScore getTextMatchScore(String algorithm ,String str1, String str2) {
		return null;
	}

	@Override
	public TextAligner getTextAligner(String algorithm,String str1, String str2 ) {
		if(algorithm == null){
			return null;
		}		

		if(algorithm.equalsIgnoreCase("SWG")){
			return new SmithWatermanGotoh(str1, str2);

		}else if(algorithm.equalsIgnoreCase("NW")){
			return new NeedlemanWunsch();

		}else if(algorithm.equalsIgnoreCase("KMP")){
			return new KMP(str1, str2);
		}
		

		return null;
		
	}
}
