package factory;

import textmatchmcore.TextMatchScore;
import textaligner.TextAligner;

public interface AbstractFactory {
    TextMatchScore getTextMatchScore(String algorithm, String str1, String str2)
        throws AlignmentFactoryException;
    TextAligner getTextAligner(String algorithm, String str1, String str2)
        throws AlignmentFactoryException;
    List<String> getAlgorithms();
}
