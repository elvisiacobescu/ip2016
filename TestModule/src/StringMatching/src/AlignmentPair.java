package StringMatching.src;


import StringMatching.src.parser.Paragraph;

public class AlignmentPair {

    private Paragraph firstParagraph;
    private Paragraph secondParagraph;
    private int startFirstParagraph;
    private int startSecondParagraph;
    private double score;

    public AlignmentPair() {
    }

    public AlignmentPair(
            Paragraph firstParagrpah,
            Paragraph secondParagraph,
            int startFirstParagraph,
            int startSecondParagraph,
            double score) {

        setFirstParagraph(firstParagraph);
        setSecondParagraph(secondParagraph);

        setStartFirstParagraph(startFirstParagraph);
        setStartSecondParagraph(startSecondParagraph);

        setScore(score);
    }

    public void setFirstParagraph(Paragraph paragraph) {
        firstParagraph = paragraph;
    }

    public void setSecondParagraph(Paragraph paragraph) {
        secondParagraph = paragraph;
    }

    public void setStartFirstParagraph(int start) {
        startFirstParagraph = start;
    }

    public void setStartSecondParagraph(int start) {
        startSecondParagraph = start;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Paragraph getFirstParagraph() {
        return firstParagraph;
    }

    public Paragraph getSecondParagraph() {
        return secondParagraph;
    }

    public int getStartFirstParagraph() {
        return startFirstParagraph;
    }

    public int getStartSecondParagraph() {
        return startSecondParagraph;
    }

    public double getScore() {
        return score;
    }
}