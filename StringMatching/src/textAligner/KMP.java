package textAligner;

public final class KMP implements TextAligner {
	String text1;
	String text2;
	double score;
	/*
	 * hidden constructor
	 */
	public KMP(String firstText, String secondText) {
		super();
		text1 = firstText;
		text2 = secondText;
	}

	public void setTexts(String firstText, String secondText){
		this.text1=firstText;
		this.text2=secondText;
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
				/*("Mismatch happened, between text char "
                        + text[i] + " and pattern char " + ptrn[j]
                        + ", \nhence jumping the value of " + "j from " + j
                        + " to " + PartialMatchTable[j] + " at text index i at " + i
                        + " based on partial match table");*/
				j = PartialMatchTable[j];
			}
			i++;
			j++;

			// a match is found
			if (j == ptrnLen) {
				/* System.out.println("FOUND SUBSTRING AT i " + i + " and index:"
                        + (i - ptrnLen));
                System.out.println("Setting j from " + j + " to " + PartialMatchTable[j]);
                j = PartialMatchTable[j];*/
				return j;
			}
		}
		return j;
	}

	public void align(){

		String[] text1SplitInBlocks=splitInBlocks(text1);
		String[] text2SplitInBlocks=splitInBlocks(text2);

		for(int i=0; i<text1SplitInBlocks.length; i++){
			int[] scores=new int[text2SplitInBlocks.length];
			for(int j=0; j<text2SplitInBlocks.length;j++){
				scores[i]=searchSubString(text2SplitInBlocks[j],text1SplitInBlocks[i]);
			}
			int maxPosition=getMaxPosition(scores);
			int nrOfSpacesToAdd=scores[maxPosition];
			for(int k=0;k<text2SplitInBlocks.length-nrOfSpacesToAdd; k++){
				text1SplitInBlocks[i]=" "+text1SplitInBlocks[i];
				text2SplitInBlocks[maxPosition]=text2SplitInBlocks[maxPosition]+" ";
			}
			score+=scores[maxPosition];

		}

	}

	private int getMaxPosition(int[] vector){
		int max=vector[0];
		for(int i=0; i<vector.length; i++)
			if(max<vector[i])
				max=vector[i];

		for(int i=0; i<vector.length;i++)
			if(max==vector[i])
				return i;

		return 0;

	}

	public double getScore(){
		return (text1.length()+text2.length()-this.score);
	}

	private String[] splitInBlocks(String text){
		int dimension=text.length()/64;
		String[] textSplitInBlocks=new String[dimension];
		int j=0;
		for(int i=0; i<textSplitInBlocks.length;i++){
			textSplitInBlocks[i]=text.substring(j,j+63);
			j+=64;
		}
		return textSplitInBlocks;
	}
}
