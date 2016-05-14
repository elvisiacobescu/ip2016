package textaligner;

import java.lang.Math;

public final class KnuthMorrisPratt implements TextAligner {

    String text1;
    String text2;
    double score;

    public KnuthMorrisPratt(String firstText, String secondText) {
        super();
        text1 = firstText;
        text2 = secondText;
    }

    /**
     * TODO: Get alignment position in the first string.
     */
    public int getFirstStart() {
        return -1;
    }

    /**
     * TODO: Get alignment position in the second string.
     */
    public int getSecondStart() {
        return -1;
    }

    public void setTexts(String firstText, String secondText) {
        this.text1 = firstText;
        this.text2 = secondText;
    }

    private int[] preProcessPattern(String ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length();
        int[] PartialMatchTable = new int[ptrnLen + 1];

        PartialMatchTable[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn.charAt(i) != ptrn.charAt(j)) {
                // if there is mismatch consider next widest border
                j = PartialMatchTable[j];
            }
            i++;
            j++;
            PartialMatchTable[i] = j;
        }

        return PartialMatchTable;
    }

    /**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    private int searchSubString(String text, String ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length();
        int txtLen = text.length();

        // initialize new array and preprocess the pattern
        int[] PartialMatchTable = preProcessPattern(ptrn);

        while (i < txtLen) {
            while (j >= 0 && text.charAt(i) != ptrn.charAt(j)) {
                j = PartialMatchTable[j];
            }
            i++;
            j++;

            // a match is found
            if (j == ptrnLen) {
                return j;
            }
        }
        return j;
    }

    public void align() {
        String[] firstTextBlocks = splitInBlocks(text1);
        String[] secondTextBlocks = splitInBlocks(text2);

        int scoresLength = Math.max(secondTextBlocks.length,
                                    firstTextBlocks.length);
        int[] scores = new int[scoresLength];

        for (int i = 0; i < firstTextBlocks.length; i++) {

            for (int j = 0; j < secondTextBlocks.length; j++) {
                scores[i] = searchSubString(secondTextBlocks[j],
                                            firstTextBlocks[i]);
            }

            int maxPosition = getMaxPosition(scores);
            int padding = scores[maxPosition];

            for (int j = 0; j < secondTextBlocks.length - padding; j++) {
                firstTextBlocks[i] = " " + firstTextBlocks[i];
                secondTextBlocks[maxPosition] += " ";
            }
            score += scores[maxPosition];
        }

    }

    private int getMaxPosition(int[] vector) {
        int max = vector[0];
        int i_max = 0;

        for (int i = 1; i < vector.length; i++) {
            if(max < vector[i]) {
                max = vector[i];
                i_max = i;
            }
        }

        return i_max;
    }

    public double getScore() {
        return text1.length() + text2.length() - this.score;
    }

    private String[] splitInBlocks(String text) {
        int dimension = text.length() / 64;
        String[] textBlocks = new String[dimension];

        int j = 0;
        for (int i = 0; i < textBlocks.length; i++) {
            textBlocks[i] = text.substring(j, j + 63);
            j += 64;
        }

        return textBlocks;
    }
}
