package StringMatching.src.factory;

import StringMatching.src.textaligner.TextAligner;
import StringMatching.src.textmatchscore.TextMatchScore;

import java.util.List;


public interface AbstractFactory {
    TextMatchScore getTextMatchScore(String algorithm, String str1, String str2)
            throws AlignmentFactoryException;

    TextAligner getTextAligner(String algorithm, String str1, String str2)
            throws AlignmentFactoryException;

    List<String> getAlgorithms();
}
