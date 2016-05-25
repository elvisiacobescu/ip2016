package textmatchscore;

public final class LongestCommonSubsequence implements TextMatchScore {

	private String firstString;
	private String secondString;

	public LongestCommonSubsequence(String firstString, String secondString) {
		this.firstString = firstString;
		this.secondString = secondString;
	}

    public String lcs() {
        int l1 = firstString.length();
        int l2 = secondString.length();

        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (firstString.charAt(i) == secondString.charAt(j)) {
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
                }
            }
        }

        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        while (i < l1 && j < l2) {
            if (firstString.charAt(i) == secondString.charAt(j)) {
                sb.append(firstString.charAt(i));
                i++;
                j++;
            } else if (arr[i + 1][j] >= arr[i][j + 1]) {
                i++;
            } else {
                j++;
            }
        }
        return sb.toString();
    }

	public double getScore() {
		return lcs().length() / (double) Math.min(firstString.length(), secondString.length());
	}
}
