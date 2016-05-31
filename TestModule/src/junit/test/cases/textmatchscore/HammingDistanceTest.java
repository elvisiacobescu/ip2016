package junit.test.cases.textmatchscore;


import StringMatching.src.textmatchscore.AlignmentScoreException;
import StringMatching.src.textmatchscore.HammingDistance;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 09.05.2016.
 */
public class HammingDistanceTest {
    @Test
    public void getHammingDistance() throws Exception {

        HammingDistance hd = new HammingDistance("abcdefgh", "abcdefhg");
        assert (hd.getHammingDistance() == 2) : "Error in computed distance, expected 2, got " + hd.getHammingDistance();

    }

    @Test
    public void getHammingDistanceForNumbers() throws Exception {

        HammingDistance hd = new HammingDistance("123456789", "123456798");
        assert (hd.getHammingDistance() == 2) : "Error in computed distance, expected 2, got " + hd.getHammingDistance();

    }


    @Test
    public void getHammingDistanceForNumbersTwo() throws Exception {

        HammingDistance hd = new HammingDistance("123456789", "987654321");
        assert (hd.getHammingDistance() == 8) : "Error in computed distance, expected 2, got " + hd.getHammingDistance();

    }


    @Test
    public void getHammingDistanceForNumbersWithCommas() throws Exception {

        HammingDistance hd = new HammingDistance("12345,6789", "98,7654321");
        assert (hd.getHammingDistance() == 10) : "Error in computed distance, expected 2, got " + hd.getHammingDistance();

    }

    /**
     * Creem o regula pentru exceptia asteptata sa apara
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Testeaza daca se arunca exceptia corecta in caz ca lungimile sunt diferite
     * in contextul a doua stringuri target
     *
     * @throws AlignmentScoreException
     */
    @Test
    public void getHammingDistanceForNumbersOfDifferentLengths() throws AlignmentScoreException {

        HammingDistance hd = new HammingDistance("12345", "1234");
        exception.expect(AlignmentScoreException.class);
        hd.getHammingDistance();
    }


    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void compareContentsOfTwoFiles() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        HammingDistance hd = new HammingDistance(testFileOne, testFileTwo);
        System.out.println(hd.getHammingDistance());
        // textele difera prin typos si numar de spatii, testul trece
    }


    @Test
    public void checkHammingDistanceImplementationFailures() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        HammingDistance hd = new HammingDistance(testFileOne, testFileTwo);

        System.out.println(hd.getHammingDistance());
        // MERGE
    }
}