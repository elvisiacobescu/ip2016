package junit.test.cases.textaligner;

import StringMatching.src.textaligner.NeedlemanWunschGotoh;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class NeedlemanWunschGotohTest {

    NeedlemanWunschGotoh needlemanWunschGotoh = new NeedlemanWunschGotoh("abc", "abed");


    /**
     * Gaseste cate litere/simboluri din cele 2 stringuri coincid
     */
    @Test
    public void NeedlemanWunschGotohTest() {
        needlemanWunschGotoh.align();
        assert (needlemanWunschGotoh.getSimilarity() == 2) : "Similarity index wrong, expected 2, got " + needlemanWunschGotoh.getSimilarity();
    }

    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void readFromFileThenCompareWithNW() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        needlemanWunschGotoh = new NeedlemanWunschGotoh(testFileOne, testFileTwo);
        needlemanWunschGotoh.align();
        System.out.println(needlemanWunschGotoh.getSimilarity());
        // textele difera prin typos si numar de spatii, testul trece
    }


    @Test
    public void readFromTwoHTMLFilesWithNeedleman() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        needlemanWunschGotoh = new NeedlemanWunschGotoh(testFileOne, testFileTwo);
        needlemanWunschGotoh.align();
        System.out.println(needlemanWunschGotoh.getSimilarity());
        // MERGE
    }

}