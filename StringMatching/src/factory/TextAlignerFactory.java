package factory;

import textMatchScore.TextMatchScore;
import textAligner.*;

public class TextAlignerFactory extends AbstractFactory {

    @Override
    public TextMatchScore getTextMatchScore(String algorithm, String str1, String str2) {
        String err = "Use a TextMatchScoreFactory for obtaining scores.";
        throw new AlignmentFactoryException(err);
    }

    private String getUsage() {
        StringBuilder errorBuilder = new StringBuilder();

        errorBuilder.append("You must choose a text alignment algorithm. ");
        errorBuilder.append("Valid choices are: ");
        errorBuilder.append("\t* SWG: SmithWatermanGotoh (local sequence alignment)");
        errorBuilder.append("\t* NW: NeedlemanWunsch (global sequence alignment)");
        errorBuilder.append("\t* KMP: Knuth-Morris-Pratt (string matching)");

        return errorBuilder.toString();
    }

    @Override
    public TextAligner getTextAligner(String algorithm, String str1, String str2) {
        if (algorithm == null) {
            throw new AlignmentFactoryException(getUsage());
        }

        if (algorithm.equalsIgnoreCase("SWG")) {
            return new SmithWatermanGotoh(str1, str2);

        } else if (algorithm.equalsIgnoreCase("NW")) {
            return new NeedlemanWunsch(str1, str2);

        } else if (algorithm.equalsIgnoreCase("KMP")) {
            return new KMP(str1, str2);
        }

        throw new AlignmentFactoryException(getUsage());
    }
}
