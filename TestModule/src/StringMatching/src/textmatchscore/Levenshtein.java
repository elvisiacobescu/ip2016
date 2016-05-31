package StringMatching.src.textmatchscore;

public final class Levenshtein implements TextMatchScore {

    private String firstString;
    private String secondString;
    private int[][] matrix;

    public Levenshtein(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public double getScore() {
        setupMatrix();
        double lev = matrix[firstString.length()][secondString.length()];
        return 1 - (lev / Math.min(firstString.length(), secondString.length()));
    }

    private void setupMatrix() {
        matrix = new int[firstString.length() + 1][secondString.length() + 1];

        for (int i = 0; i <= firstString.length(); i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j <= secondString.length(); j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    int minimum = Integer.MAX_VALUE;

                    if (matrix[i - 1][j] + 1 < minimum) {
                        minimum = matrix[i - 1][j] + 1;
                    }

                    if (matrix[i][j - 1] + 1 < minimum) {
                        minimum = matrix[i][j - 1] + 1;
                    }

                    if (matrix[i - 1][j - 1] + 1 < minimum) {
                        minimum = matrix[i - 1][j - 1] + 1;
                    }

                    matrix[i][j] = minimum;
                }
            }
        }
    }
}