package junit.test.cases;

import StringMatching.src.textmatchscore.Levenshtein;
import org.junit.Test;

/**
 * Created by Mihai on 16.05.2016.
 *
 */
public class LevenshteinTest {

    private Levenshtein levenshtein;

    /**
     * The answer is 1
     * @throws Exception
     */
    @Test
    public void getScoreForOneDifferentLetter() throws Exception {
        levenshtein = new Levenshtein("ceva", "Deva");
        assert (levenshtein.getScore() == 1);
    }

    /**
     * Should be zero
     */
    @Test
    public void getScoreForNullStrings(){
        levenshtein = new Levenshtein("","");
        assert (levenshtein.getScore() == 0);
    }

    @Test
    public void getScoreForWikipediaExample(){
        levenshtein = new Levenshtein("MARTHA", "MARHTA");
        assert (levenshtein.getScore() == 2);
    }
}