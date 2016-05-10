package junit.test.cases;

import Subjects.HammingDistance;
import org.junit.Test;

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


    @Test
    public void getHammingDistanceForNumbersOfDifferentLengths() throws Exception {

        HammingDistance hd = new HammingDistance("12345", "1234");
        assert (hd.getHammingDistance() == -1) : "Error in computed distance, expected -1, got " + hd.getHammingDistance();

    }

}