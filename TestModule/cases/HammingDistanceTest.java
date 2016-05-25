package junit.test.cases;


import StringMatching.src.textmatchscore.AlignmentScoreException;
import StringMatching.src.textmatchscore.HammingDistance;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Mihai on 09.05.2016.
 *
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
     * @throws AlignmentScoreException
     */
    @Test
    public void getHammingDistanceForNumbersOfDifferentLengths() throws AlignmentScoreException {

        HammingDistance hd = new HammingDistance("12345", "1234");
        exception.expect(AlignmentScoreException.class);
        hd.getHammingDistance();
    }


}