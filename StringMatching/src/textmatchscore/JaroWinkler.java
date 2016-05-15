package textmatchscore;

public class JaroWinkler implements TextMatchScore {
    private String firstString;
    private String secondString;

    private String theMatchA = "";
    private String theMatchB = "";
    private int mRange = -1;

    public JaroWinkler(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public double getScore() {
        return getSimilarity();
    }

    public double getSimilarity() {
        mRange = Math.max(firstString.length(), secondString.length()) / 2 - 1;

        int m = getMatch();
        int t = 0;

        if (getMismatch(secondString, firstString) > 0) {
            t = getMismatch(firstString, secondString) / getMismatch(secondString, firstString);
        }

        int l1 = firstString.length();
        int l2 = secondString.length();

        double f = 0.3333;
        double mt = (double) (m - t) / m;
        double jw = f * ((double) m / l1 + (double) m / l2 + mt);
        double res = jw + getCommonPrefix(firstString, secondString) * (0.1 * (1.0 - jw));

        return res;
    }

    private int getMatch() {

        theMatchA = "";
        theMatchB = "";

        int matches = 0;

        for (int i = 0; i < firstString.length(); i++) {

            //Look backward
            int counter = 0;
            while(counter <= mRange && i >= 0 && counter <= i) {
                if (firstString.charAt(i) == secondString.charAt(i - counter)) {
                    matches++;
                    theMatchA = theMatchA + firstString.charAt(i);
                    theMatchB = theMatchB + secondString.charAt(i);
                }
                counter++;
            }

            //Look forward
            counter = 1;
            while(counter <= mRange && i < secondString.length() && counter + i < secondString.length()) {
                if (firstString.charAt(i) == secondString.charAt(i + counter)) {
                    matches++;
                    theMatchA = theMatchA + firstString.charAt(i);
                    theMatchB = theMatchB + secondString.charAt(i);
                }
                counter++;
            }
        }
        return matches;
    }

    private int getMismatch(String s1, String s2)
    {
        int transPositions = 0;

        for (int i = 0; i < theMatchA.length(); i++)
        {
            //Look Backward
            int counter = 0;
            while(counter <= mRange && i >= 0 && counter <= i)
            {
                if (theMatchA.charAt(i) == theMatchB.charAt(i - counter) && counter > 0)
                {
                    transPositions++;
                }
                counter++;
            }

            //Look forward
            counter = 1;
            while(counter <= mRange && i < theMatchB.length() && (counter + i) < theMatchB.length())
            {
                if (theMatchA.charAt(i) == theMatchB.charAt(i + counter) && counter > 0)
                {
                    transPositions++;
                }
                counter++;
            }
        }
        return transPositions;
    }

    private int getCommonPrefix(String firstString, String secondString)
    {
        int cp = 0;
        for (int i = 0; i < 4; i++)
        {
            if (firstString.charAt(i) == secondString.charAt(i)) cp++;
        }
        return cp;
    }
}
