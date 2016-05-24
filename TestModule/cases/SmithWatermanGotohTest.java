package junit.test.cases.textaligner;

import StringMatching.src.textaligner.SmithWatermanGotoh;
import org.junit.Test;

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
    public void testForSmithWatermanGotoh(){
        smithWatermanGotoh = new SmithWatermanGotoh();
        smithWatermanGotoh.setFirstText("abcdefghij");
        smithWatermanGotoh.setSecondText("abcdghij");
        smithWatermanGotoh.align();
        assert (smithWatermanGotoh.getSimilarity() == 8):"Indice de similaritate eronat";
    }


}