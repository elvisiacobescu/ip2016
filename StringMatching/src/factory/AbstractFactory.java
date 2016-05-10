package factory;

import textAligner.TextAligner;
import textMatchScore.TextMatchScore;

public abstract class AbstractFactory {
	abstract TextMatchScore getTextMatchScore(String algorithm );
	abstract TextAligner getTextAligner(String algorithm );
}
