package factory;

import java.util.ArrayList;
import java.util.List;

import textaligner.TextAligner;
import textmatchscore.*;

public final class TextMatchScoreFactory implements AbstractFactory {

    public TextMatchScore getTextMatchScore(String algorithm, String str1, String str2)
            throws AlignmentFactoryException {

        if (algorithm == null) {
            throw new AlignmentFactoryException(getUsage());
        }

        if (algorithm.equalsIgnoreCase("HammingDistance")) {
            return new HammingDistance(str1, str2);

        } else if(algorithm.equalsIgnoreCase("JaroWinkler")) {
            return new JaroWinkler(str1, str2);

        } else if(algorithm.equalsIgnoreCase("Levenshtein")) {
            return new Levenshtein(str1, str2);
        }
        else if (algorithm.equalsIgnoreCase("LCS")) {
            return new LongestCommonSubsequence(str1, str2);
        }

        throw new AlignmentFactoryException(getUsage());
    }

    private String getUsage() {
        StringBuilder errorBuilder = new StringBuilder();

        errorBuilder.append("You must choose a score calculator. ");
        errorBuilder.append("Valid choices are: ");
        errorBuilder.append("\t* HammingDistance: character comparison by position");
        errorBuilder.append("\t* Levenshtein: edit distance (number of edits required for texts to match)");
        errorBuilder.append("\t* LCS: Longest Common Subsequence of both texts");
        errorBuilder.append("\t* JaroWinkler: similarity between texts");

        return errorBuilder.toString();
    }

    public TextAligner getTextAligner(String algorithm, String str1, String str2)
            throws AlignmentFactoryException {
        String err = "Use a TextAlignerFactory for string alignment.";
        throw new AlignmentFactoryException(err);
    }

    public List<String> getAlgorithms() {
        ArrayList<String> allAlgorithms = new ArrayList<String>();
        allAlgorithms.add("HammingDistance");
        allAlgorithms.add("JaroWinkler");
        allAlgorithms.add("Levenshtein");
        allAlgorithms.add("LCS");

        return allAlgorithms;
    }
}
