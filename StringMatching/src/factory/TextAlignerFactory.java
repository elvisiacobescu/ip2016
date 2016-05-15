package factory;

import java.util.ArrayList;
import java.util.List;

import textmatchscore.TextMatchScore;
import textaligner.*;

public final class TextAlignerFactory implements AbstractFactory {

    TextAlignerFactory() {
    }

    public TextMatchScore getTextMatchScore(String algorithm, String str1, String str2)
            throws AlignmentFactoryException {
        String err = "Use a TextMatchScoreFactory for obtaining scores.";
        throw new AlignmentFactoryException(err);
    }

    private String getUsage() {
        StringBuilder errorBuilder = new StringBuilder();

        errorBuilder.append("You must choose a text alignment algorithm. ");
        errorBuilder.append("Valid choices are: ");
        errorBuilder.append("\t* SWG: Smith-Waterman-Gotoh (local sequence alignment)");
        errorBuilder.append("\t* NWG: Needleman-Wunsch-Gotoh (global sequence alignment)");
        errorBuilder.append("\t* KMP: Knuth-Morris-Pratt (string matching)");

        return errorBuilder.toString();
    }

    public TextAligner getTextAligner(String algorithm, String str1, String str2)
            throws AlignmentFactoryException {
        if (algorithm == null) {
            throw new AlignmentFactoryException(getUsage());
        }

        if (algorithm.equalsIgnoreCase("SWG")) {
            return new SmithWatermanGotoh(str1, str2);

        } else if (algorithm.equalsIgnoreCase("NWG")) {
            return new NeedlemanWunschGotoh(str1, str2);

        } else if (algorithm.equalsIgnoreCase("KMP")) {
            return new KnuthMorrisPratt(str1, str2);
        }

        throw new AlignmentFactoryException(getUsage());
    }

    public List<String> getAlgorithms() {
        ArrayList<String> allAlgorithms = new ArrayList<String>();
        allAlgorithms.add("SWG");
        allAlgorithms.add("NWG");
        allAlgorithms.add("KMP");

        return allAlgorithms;
    }
}
