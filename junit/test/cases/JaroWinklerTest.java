package junit.test.cases;

import Subjects.JaroWinkler;
import org.junit.Test;

/**
 * Created by Mihai on 09.05.2016.
 *
 */
public class JaroWinklerTest {

    JaroWinkler jaroWinkler;

    @Test
    public void getSimilarityValueTest() throws Exception {

        jaroWinkler = new JaroWinkler();
        String one = "MARTHA";
        String two = "MARHTA";
        double testSim = jaroWinkler.getSimilarity(one, two);
        assert(testSim == 0.961045):"Expected value: "+0.961045+" Actual value "+testSim;

    }

    @Test
    public void getSimilarityForWhitespace() throws Exception{

        jaroWinkler = new JaroWinkler();
        String one = "       ";
        String two = "       ";
        double testSim = jaroWinkler.getSimilarity(one, two);
        assert (testSim == 2.2500612807881772): "Value not correspondent ("+testSim+")";

    }


    @Test
    public void getSimilarityLengthDifferences() throws Exception{

        jaroWinkler = new JaroWinkler();
        String one = "mandar";
        String two = "mandarine";
        double testSim = jaroWinkler.getSimilarity(one, two);

        assert (testSim == 0.93): "Value not correspondent ("+testSim+")";

    }
}