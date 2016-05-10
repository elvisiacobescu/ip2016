package factory;

import textAligner.TextAligner;
import textMatchScore.TextMatchScore;

public class TextAlignerFactory extends AbstractFactory{

	@Override 
	TextMatchScore getTextMatchScore(String algorithm ) {
		return null;
	}

	@Override
	public TextAligner getTextAligner(String algorithm ) {
		return null;
	}
}
