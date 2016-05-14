package factory;

import textMatchScore.TextMatchScore;
import textAligner.TextAligner;

public abstract class AbstractFactory {
    public abstract TextMatchScore getTextMatchScore(String algorithm, String str1, String str2)
        throws AlignmentFactoryException;
    public abstract TextAligner getTextAligner(String algorithm, String str1, String str2)
        throws AlignmentFactoryException;
}
