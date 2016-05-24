package junit.test.cases.textaligner;

import StringMatching.src.textaligner.NeedlemanWunschGotoh;
import org.junit.Test;

/**
 * Created by Mihai on 16.05.2016.
 *
 */
public class NeedlemanWunschGotohTest {

    NeedlemanWunschGotoh needlemanWunschGotoh = new NeedlemanWunschGotoh("abc", "abed");


    /**
     * Gaseste cate litere/simboluri din cele 2 stringuri coincid
     */
    @Test
    public void NeedlemanWunschGotohTest(){
        needlemanWunschGotoh.align();
        assert (needlemanWunschGotoh.getSimilarity() == 2):"Similarity index wrong, expected 2, got " + needlemanWunschGotoh.getSimilarity();
    }
}