package textmatchscore;

public final class HammingDistance implements TextMatchScore {

    private String compOne;
    private String compTwo;

    public HammingDistance(String one, String two) {
        compOne = one;
        compTwo = two;
    }

    public HammingDistance() {
    }

    public int getHammingDistance() throws AlignmentScoreException {

        if (compOne.length() != compTwo.length()) {
            StringBuilder err = new StringBuilder();
            err.append("Hamming Distance is undefined ");
            err.append("for sequences of different lengths.");

            throw new AlignmentScoreException(err.toString());
        }

        int diffs = 0;
        for (int i = 0; i < compOne.length(); i++) {
            if (compOne.charAt(i) != compTwo.charAt(i)) {
                diffs++;
            }
        }

        return diffs;
    }

    public double getScore() throws AlignmentScoreException {
        return getHammingDistance();
    }
}
