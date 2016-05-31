package junit.test.cases.textmatchscore;

import StringMatching.src.textmatchscore.Levenshtein;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class LevenshteinTest {

    private Levenshtein levenshtein;

    /**
     * The answer is 1
     *
     * @throws Exception
     */
    @Test
    public void getScoreForOneDifferentLetter() throws Exception {
        levenshtein = new Levenshtein("ceva", "Deva");
        System.out.println(levenshtein.getScore());
    }

    /**
     * Should be zero
     */
    @Test
    public void getScoreForNullStrings() {
        levenshtein = new Levenshtein("", "");
        System.out.println(levenshtein.getScore());        //assert (levenshtein.getScore() == 0);    }
    }

    @Test
    public void getScoreForWikipediaExample() {
        levenshtein = new Levenshtein("MARTHA", "MARHTA");
        System.out.println(levenshtein.getScore());
    }

    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void readFromFileThenCompareWithLeven() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        levenshtein = new Levenshtein(testFileOne, testFileTwo);

        System.out.println(levenshtein.getScore());
        // textele difera prin typos si numar de spatii, testul trece
    }


    @Test
    public void readFromTwoHTMLFiles() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        levenshtein = new Levenshtein(testFileOne, testFileTwo);

        System.out.println(levenshtein.getScore());
        // MERGE
    }
}