import parser.Paragraph;

public class AlignmentPair {

    private Paragraph firstParagraph;
    private Paragraph secondParagraph;
    private int startFirstParagraph;
    private int startSecondParagraph;
    private double score;

    public AlignmentPair() {
    }

    public AlignmentPair(
            Paragraph firstParagraph,
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
    
    @Override
	public String toString(){
		//return "(" + this.getFirstParagraph().getParagraphNum() + "," + this.getSecondParagraph().getParagraphNum() + "," + this.getScore() + ")";
    	String str = "";
    	str += this.firstParagraph;
    	str += " start: " + this.startFirstParagraph + '\n';
    	str += this.secondParagraph;
    	str += " start: " + this.startSecondParagraph + '\n';
    	str += "Score: " + this.score;
    	return str;
    }
}
