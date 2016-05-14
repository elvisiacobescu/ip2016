package textmatchscore;

/**
 ** Java Program to implement Longest Common Subsequence Algorithm
 **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Class  LongestCommonSubsequence **/
public class  LongestCommonSubsequence implements TextMatchScore{

	private String str1;
	private String str2;

	public LongestCommonSubsequence(String str1, String str2){
		this.str1 = str1;
		this.str2 = str2;
	}

    public LongestCommonSubsequence() {
		// TODO Auto-generated constructor stub
	}

	/** function lcs **/
    public String lcs()
    {
        int l1 = str1.length();
        int l2 = str2.length();

        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int i = l1 - 1; i >= 0; i--)
        {
            for (int j = l2 - 1; j >= 0; j--)
            {
                if (str1.charAt(i) == str2.charAt(j))
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }

        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        while (i < l1 && j < l2)
        {
            if (str1.charAt(i) == str2.charAt(j))
            {
                sb.append(str1.charAt(i));
                i++;
                j++;
            }
            else if (arr[i + 1][j] >= arr[i][j + 1])
                i++;
            else
                j++;
        }
        return sb.toString();
    }

    /** Main Function **/
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Longest Common Subsequence Algorithm Test\n");

        System.out.println("\nEnter string 1");
        String str1 = br.readLine();

        System.out.println("\nEnter string 2");
        String str2 = br.readLine();

        LongestCommonSubsequence obj = new LongestCommonSubsequence(str1,str2);
        String result = obj.lcs();

        System.out.println("\nLongest Common Subsequence : "+ result);
    }

	/**
	 * @return the compOne
	 */
	public String getCompOne() {
		return str1;
	}

	/**
	 * @param compOne the compOne to set
	 */
	public void setCompOne(String compOne) {
		this.str1 = compOne;
	}

	/**
	 * @return the compTwo
	 */
	public String getCompTwo() {
		return str2;
	}

	/**
	 * @param compTwo the compTwo to set
	 */
	public void setCompTwo(String compTwo) {
		this.str2 = compTwo;
	}

	@Override
	public double getScore() {
		return this.lcs().length();
	}
}
