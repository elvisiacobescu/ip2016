package junit.test.cases.textmatchscore;


import StringMatching.src.textmatchscore.AlignmentScoreException;
import StringMatching.src.textmatchscore.LongestCommonSubsequence;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class LongestCommonSubsequenceTest {


    LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("zzzzzzzzzalfabetghghghghghghhghgh", "alfabetizare intensiva");

    /**
     * Testam daca obiectul LongestCommonSubsequence gaseste proper LCS
     *
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
        assert (longestCommonSubsequence.getScore() == 7) : "Failed!" + "expected: 7.0, " + "got " + longestCommonSubsequence.getScore();
    }


    @Test
    public void lcsDifferentLength() throws Exception {

        longestCommonSubsequence = new LongestCommonSubsequence("longest common subsequence", "longest common subsequencecececececececececececcececece");
        longestCommonSubsequence.lcs();
        assert (longestCommonSubsequence.getScore() == 26);

    }


    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void readFromFileThenCompareWithNWG() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        longestCommonSubsequence = new LongestCommonSubsequence(testFileOne, testFileTwo);
        System.out.println(longestCommonSubsequence.getScore());
        // textele difera prin typos si numar de spatii, testul trece

    }


    @Test
    public void readFromTwoHTMLFiles() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        longestCommonSubsequence = new LongestCommonSubsequence(testFileOne, testFileTwo);
        System.out.println(longestCommonSubsequence.getScore());

    }


    @Test
    public void readFromGivenHTMLFiles() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\fisiere_test\\013-MOLDOROMAN-91178-95936_gh.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\fisiere_test\\013-MOLDOROMAN-91178-95936_gh.html")));
        longestCommonSubsequence = new LongestCommonSubsequence(testFileOne, testFileTwo);
        System.out.println("Test designed to fail, due to inabiility of class to process entire files");

    }
}