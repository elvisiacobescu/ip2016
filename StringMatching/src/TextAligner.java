import jaligner.*;
import jaligner.matrix.*;

class TextAligner {
    private String firstString;
    private String secondString;
    private Sequence firstSequence;
    private Sequence secondSequence;
    private float score;
    private int similarity;
    private int firstStart;
    private int secondStart;

    TextAligner() {
    }

    TextAligner(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public void setFirstString(String text) {
        firstString = text;
    }

    public void setSecondString(String text) {
        secondString = text;
    }

    public String getFirstString() {
        return firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public float getScore() {
        return score;
    }

    public int getSimilarity() {
        return similarity;
    }

    public int getFirstStart() {
        return firstStart;
    }

    public int getSecondStart() {
        return secondStart;
    }

    public void align() {
        firstSequence = new Sequence(firstString);
        secondSequence = new Sequence(secondString);

        try {
            Alignment alignment =
                SmithWatermanGotoh.align(firstSequence,
                        secondSequence,
                        MatrixLoader.load("BLOSUM70"), 1.0f, 0.0f);

            score = alignment.calculateScore();
            similarity = alignment.getSimilarity();
            firstStart = alignment.getStart1();
            secondStart = alignment.getStart2();
        } catch (MatrixLoaderException err) {
            System.err.println(err);
        }
    }
}
