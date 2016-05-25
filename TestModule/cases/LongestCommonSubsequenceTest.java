package junit.test.cases;


import StringMatching.src.textmatchscore.LongestCommonSubsequence;
import org.junit.Test;

/**
 * Created by Mihai on 16.05.2016.
 *
 */
public class LongestCommonSubsequenceTest {


    LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("zzzzzzzzzalfabetghghghghghghhghgh","alfabetizare intensiva");
    /**
     * Testam daca obiectul LongestCommonSubsequence gaseste proper LCS
     * @input 2 secvente de caractere cu un subsir comun
     * @output lungimea efectiva a celui mai lung subsir comun
     */

    @Test
    public void lcsSameLength() throws Exception {
        /**
         * calculam scorul folosind metoda clasei
         */

        longestCommonSubsequence.lcs();
        /**
         * calculand naiv, se obtine sirul 'alfabet' de lungime 7 ca fiind cel mai lung
         */
        assert (longestCommonSubsequence.getScore() == 7):"Failed!"+ "expected: 7.0, "+ "got "+longestCommonSubsequence.getScore();
    }



    @Test
    public void lcsDifferentLength() throws Exception{
        longestCommonSubsequence = new LongestCommonSubsequence("longest common subsequence", "longest common subsequencecececececececececececcececece");
        longestCommonSubsequence.lcs();
        assert (longestCommonSubsequence.getScore() == 26);
    }
}