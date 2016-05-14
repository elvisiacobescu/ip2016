package factory;

import textAligner.TextAligner;
import textMatchScore.*;

public class TextMatchScoreFactory extends AbstractFactory {

    @Override
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

    @Override
    public TextAligner getTextAligner(String algorithm, String str1, String str2)
            throws AlignmentFactoryException {
        String err = "Use a TextAlignerFactory for string alignment.";
        throw new AlignmentFactoryException(err);
    }
}
