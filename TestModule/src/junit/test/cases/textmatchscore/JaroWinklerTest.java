package junit.test.cases.textmatchscore;

import StringMatching.src.textmatchscore.AlignmentScoreException;
import StringMatching.src.textmatchscore.JaroWinkler;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 */
public class JaroWinklerTest {

    /**
     * Primeste ca input 2 stringuri de lungime mica, returneaza un indice de similaritate
     * calculat ca 1-sim, unde sim e obtinuta din functie
     *
     * @throws Exception
     */
    @Test
    public void getScoreForNullStrings() throws Exception {
        JaroWinkler jw = new JaroWinkler("", "");
        assert (jw.getScore() == 1);
    }

    @Test
    public void getScoreForProperStrings() {
        JaroWinkler jw = new JaroWinkler("a", "a");
        assert (jw.getScore() == 0);

    }

    @Test
    public void getScoreForDifferentChars() {
        JaroWinkler jw = new JaroWinkler("a", "B");
        System.out.println(jw.getScore());
    }

    @Test
    public void getScoreForTextbookExample() {
        JaroWinkler jw = new JaroWinkler("MARTHA", "MARHTA");
        assert (jw.getScore() == 0.03888893127441406);
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
        JaroWinkler jaroWinkler = new JaroWinkler(testFileOne, testFileTwo);
        System.out.println(jaroWinkler.getScore());
        // textele difera prin typos si numar de spatii, testul trece
    }


    @Test
    public void readFromTwoHTMLFiles() throws IOException, AlignmentScoreException {

        String testFileOne, testFileTwo;
        testFileOne = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML.html")));
        testFileTwo = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\testFileHTML_2.html")));
        JaroWinkler jaroWinkler = new JaroWinkler(testFileOne, testFileTwo);

        System.out.println(jaroWinkler.getScore());
        // MERGE
    }
}