/**
 *
 * Adapted from: https://github.com/tdebatty/java-string-similarity
 *
 */
package textmatchscore;

import java.util.Arrays;

/**
 * The Jaro–Winkler distance metric is designed and best suited for short
 * strings such as person names, and to detect typos; it is (roughly) a
 * variation of Damerau-Levenshtein, where the substitution of 2 close
 * characters is considered less important then the substitution of 2 characters
 * that a far from each other.
 * Jaro-Winkler was developed in the area of record linkage (duplicate
 * detection) (Winkler, 1990). It returns a value in the interval [0.0, 1.0].
 * The distance is computed as 1 - Jaro-Winkler similarity.
 * @author Thibault Debatty
 */
public class JaroWinkler implements TextMatchScore {

    private String firstString;
    private String secondString;

    private double threshold = 0.7;

    public JaroWinkler(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public double getScore() {
        return 1.0 - similarity();
    }

    private final void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    private double getThreshold() {
        return threshold;
    }

    private double similarity() {
        int[] mtp = matches();
        float m = mtp[0];
        if (m == 0) {
            return 0f;
        }
        float j = ((m / firstString.length() + m / secondString.length() + (m - mtp[1]) / m)) / 3;
        float jw = j < getThreshold() ? j : j + Math.min(0.1f, 1f / mtp[3]) * mtp[2]
                * (1 - j);
        return jw;
    }

    private int[] matches() {
        String max, min;
        if (firstString.length() > secondString.length()) {
            max = firstString;
            min = secondString;
        } else {
            max = secondString;
            min = firstString;
        }
        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0),
                    xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchIndexes[mi] = xi;
                    matchFlags[xi] = true;
                    matches++;
                    break;
                }
            }
        }
        char[] mfirstString = new char[matches];
        char[] msecondString = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) {
                mfirstString[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) {
                msecondString[si] = max.charAt(i);
                si++;
            }
        }
        int transpositions = 0;
        for (int mi = 0; mi < mfirstString.length; mi++) {
            if (mfirstString[mi] != msecondString[mi]) {
                transpositions++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            if (firstString.charAt(mi) == secondString.charAt(mi)) {
                prefix++;
            } else {
                break;
            }
        }
        return new int[]{matches, transpositions / 2, prefix, max.length()};
    }
}
