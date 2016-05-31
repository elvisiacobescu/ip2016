package textaligner;

import java.lang.Math;

public final class KnuthMorrisPratt implements TextAligner {

    private static final int CHUNKSIZE = 32;

    private String firstText;
    private String secondText;
    private int firstStart;
    private int secondStart;

    public KnuthMorrisPratt(String firstText, String secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }

    public int getFirstStart() {
        return firstStart;
    }

    public int getSecondStart() {
        return secondStart;
    }

    public void setTexts(String firstText, String secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }

    public void align() {
        String bigger;
        String smaller;

        if (firstText.length() > secondText.length()) {
            bigger = firstText;
            smaller = secondText;
        } else {
            smaller = firstText;
            bigger = secondText;
        }

        String[] blocks = splitInBlocks(smaller);
        int[] matches = new int[blocks.length];

        for (int block = 0; block < blocks.length; block++) {
            matches[block] = searchSubString(bigger, blocks[block]);
        }

        int maxBlock = getMaxMatch(matches);
        int match = matches[maxBlock];

        if (match == CHUNKSIZE) {
            int smallerStart = 0;

            int biggerStart = bigger.indexOf(
                    smaller.substring(maxBlock * match,
                                      maxBlock * match + CHUNKSIZE))
                - maxBlock * match;

            if (smaller.equals(firstText)) {
                firstStart = smallerStart;
                secondStart = biggerStart;
            } else {
                secondStart = smallerStart;
                firstStart = biggerStart;
            }
        }
    }

    private int getMaxMatch(int[] vector) {
        int max = vector[0];
        int i_max = 0;

        for (int i = 1; i < vector.length; i++) {
            if (max < vector[i]) {
                max = vector[i];
                i_max = i;
            }
        }

        return i_max;
    }

    private String[] splitInBlocks(String text) {
        int chunks = text.length() / CHUNKSIZE + 1;
        String[] textBlocks = new String[chunks];

        int start = 0;
        for (int i = 0; i < textBlocks.length; i++) {
            int stop = Math.min(start + CHUNKSIZE - 1, text.length());
            textBlocks[i] = text.substring(start, stop);
            start += CHUNKSIZE;
        }

        return textBlocks;
    }

    private int[] preProcessPattern(String ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length();
        int[] PartialMatchTable = new int[ptrnLen + 1];

        PartialMatchTable[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn.charAt(i) != ptrn.charAt(j)) {
                /** If there is a mismatch, consider the next widest border. */
                j = PartialMatchTable[j];
            }
            i++;
            j++;
            PartialMatchTable[i] = j;
        }

        return PartialMatchTable;
    }

    private int searchSubString(String text, String ptrn) {
        int ptrnLen = ptrn.length();
        int txtLen = text.length();

        int[] PartialMatchTable = preProcessPattern(ptrn);

        int i = 0;
        int j = 0;
        while (i < txtLen) {

            while (j >= 0 && text.charAt(i) != ptrn.charAt(j)) {
                j = PartialMatchTable[j];
            }

            i++;
            j++;

            if (j == ptrnLen) {
                /** A match is found. */
                return j + 1;
            }
        }

        return j + 1;
    }
}
