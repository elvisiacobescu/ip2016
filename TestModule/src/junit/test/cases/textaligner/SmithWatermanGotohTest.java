package junit.test.cases.textaligner;

import StringMatching.src.textaligner.SmithWatermanGotoh;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class SmithWatermanGotohTest {

    private SmithWatermanGotoh smithWatermanGotoh;


    /**
     * Returneaza acelasi lucru ca si ceilalti
     * algoritmi de testare ai literelor
     */
    @Test
    public void testForSmithWatermanGotoh() {
        smithWatermanGotoh = new SmithWatermanGotoh();
        smithWatermanGotoh.setFirstText("abcdefghij");
        smithWatermanGotoh.setSecondText("abcdghij");
        smithWatermanGotoh.align();
        assert (smithWatermanGotoh.getSimilarity() == 8) : "Indice de similaritate eronat";
    }

    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void readFromFileThenCompareWithSWG() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        smithWatermanGotoh = new SmithWatermanGotoh(testFileOne, testFileTwo);
        smithWatermanGotoh.align();
        System.out.println(smithWatermanGotoh.getSimilarity());
        // textele difera prin typos si numar de spatii, testul trece
    }


    @Test
    public void readFromTwoHTMLFiles() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        smithWatermanGotoh = new SmithWatermanGotoh(testFileOne, testFileTwo);
        smithWatermanGotoh.align();
        System.out.println(smithWatermanGotoh.getSimilarity());
        // MERGE
    }
}