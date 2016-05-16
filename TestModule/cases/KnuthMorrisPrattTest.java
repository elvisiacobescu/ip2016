package junit.test.cases;

import StringMatching.src.textaligner.KnuthMorrisPratt;
import org.junit.Test;

/**
 * Created by Mihai on 16.05.2016.
 */
public class KnuthMorrisPrattTest {

    KnuthMorrisPratt kmp = new KnuthMorrisPratt("cocacola", "co");
    @Test
    public void doesItWork(){
        kmp.align();
    }
}