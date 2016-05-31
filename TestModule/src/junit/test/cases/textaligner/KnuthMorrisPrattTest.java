package junit.test.cases.textaligner;

import StringMatching.src.textaligner.KnuthMorrisPratt;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class KnuthMorrisPrattTest {

    KnuthMorrisPratt kmp = new KnuthMorrisPratt("cocacola", "co");

    @Test
    public void doesItWork() {
        System.out.println("");
    }

    /**
     * Aplica algoritmul pe stringuri citite din fisier
     *
     * @throws IOException
     */
    @Test
    public void readFromFileThenCompareWithKMP() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileOne.txt")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileTwo.txt")));
        kmp = new KnuthMorrisPratt(testFileOne, testFileTwo);
        kmp.align();
        System.out.println(kmp.getFirstStart());
        System.out.println(kmp.getSecondStart());
    }


    @Test
    public void readFromTwoHTMLFilesThenCompareWithKMP() throws IOException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        kmp = new KnuthMorrisPratt(testFileOne, testFileTwo);
        kmp.align();
        System.out.println(kmp.getFirstStart());
        System.out.println(kmp.getSecondStart());
        // MERGE
    }
}